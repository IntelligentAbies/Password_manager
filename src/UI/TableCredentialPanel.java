package UI;

import Controller.Controller;
import Exceptions.DatabaseNotSetupped;
import Exceptions.PasswordIsWrong;
import Model.Credential;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TableCredentialPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JLabel lblFilter;
    private JButton btnCreate;
    private JButton btnFilter;
    private JButton btnRemove;
    private JTextField txtFilter;
    private JComboBox<String>cbType;
    public TableCredentialPanel() {
        setLayout(new BorderLayout());


        btnCreate = new JButton("Aggiungi Delle Credenziali");
        btnCreate.addActionListener(new CreateListener());

        btnRemove = new JButton("Rimuovi Delle Credenziali");
        btnRemove.addActionListener(new RemoveButtonListener());

        btnFilter = new JButton("Filtra");
        btnFilter.addActionListener(new FilterListener());

        txtFilter = new JTextField(20);

        lblFilter = new JLabel("Filtra Per: ");

        cbType = new JComboBox<>(new String[]{"Sito", "Username", "Email", "Telefono","Nessun Filtro"});
        cbType.setEditable(false);

        JPanel topPanel = new JPanel(new BorderLayout());

        // Pannello per i componenti a sinistra
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(lblFilter);
        leftPanel.add(cbType);
        leftPanel.add(txtFilter);
        leftPanel.add(btnFilter);
        topPanel.add(leftPanel, BorderLayout.WEST);

        //Pannello per i componenti di destra
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(btnCreate);
        rightPanel.add(btnRemove);
        topPanel.add(rightPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // creazione tabella
        String[] columnNames = {"Sito", "Username", "Email", "Telefono", "Password"};


        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tutte le celle non editabili
            }
        };

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        //inserimento della table in un scrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        //Aggiungi bordi e titolo
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Password Manager"));

        refreshTable();
        setPreferredSize (new Dimension(850, 400));
    }

    public void refreshTable() {
        Controller controller = new Controller();
        tableModel.setRowCount(0);
        try {
            List<Credential> credentials=controller.getData();
            for (Credential credential : credentials) {
                tableModel.addRow(new Object[]{credential.getSite(), credential.getUsername(), credential.getEmail(),credential.getPhoneNumber(), credential.getPassword()});
            }
        } catch (PasswordIsWrong | DatabaseNotSetupped e) {
            //Qua ci entra sempre all'inizio
            //JOptionPane.showMessageDialog(null,"La password è sbagliata : (");
        }
    }

    public void refreshTable(List<Credential> credentials) {
        tableModel.setRowCount(0);
        for (Credential credential : credentials) {
            tableModel.addRow(new Object[]{credential.getSite(), credential.getUsername(), credential.getEmail(),credential.getPhoneNumber(), credential.getPassword()});
        }
    }

    public class FilterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String filter = txtFilter.getText();
            String type = cbType.getSelectedItem().toString();
            Controller controller = new Controller();
            try {
                List<Credential> credentials = controller.findType(type,filter);
                refreshTable(credentials);
            } catch (PasswordIsWrong ex) {
                JOptionPane.showMessageDialog(null,"La password è sbagliata : (");
            }
        }
    }

    public class CreateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JPanel parent = (JPanel) TableCredentialPanel.this.getParent();
            CardLayout cl = (CardLayout) parent.getLayout();
            cl.show(parent, "AddCredentialPanel");
        }
    }

    public class RemoveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JPanel parent = (JPanel) TableCredentialPanel.this.getParent();
            CardLayout cl = (CardLayout) parent.getLayout();
            cl.show(parent, "RemoveCredentialPanel");
        }
    }
}
