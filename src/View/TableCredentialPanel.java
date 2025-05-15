package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

        btnRemove = new JButton("Rimuovi Delle Credenziali");

        btnFilter = new JButton("Filtra");

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

        setPreferredSize (new Dimension(850, 400));
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getBtnCreate() {
        return btnCreate;
    }

    public JButton getBtnFilter() {
        return btnFilter;
    }

    public JButton getBtnRemove() {
        return btnRemove;
    }

    public JTextField getTxtFilter() {
        return txtFilter;
    }

    public JComboBox<String> getCbType() {
        return cbType;
    }
}
