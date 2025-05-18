package View;

import View.CustomElements.CustomButton;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableCredentialPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JLabel lblFilter;
    private CustomButton btnCreate;
    private CustomButton btnFilter;
    private CustomButton btnRemove;
    private JTextField txtFilter;
    private JComboBox<String>cbType;
    public TableCredentialPanel() {
        setLayout(new BorderLayout());


        btnCreate = new CustomButton("Aggiungi");

        btnRemove = new CustomButton("Rimuovi");

        btnFilter = new CustomButton("Filtra");

        txtFilter = new JTextField(20);
        txtFilter.setCaretColor(Color.WHITE);
        txtFilter.setForeground(Color.WHITE);
        txtFilter.setBackground(new Color(35,35,35));

        lblFilter = new JLabel("Filtra Per: ");
        lblFilter.setForeground(Color.WHITE);

        cbType = new JComboBox<>(new String[]{"Sito", "Username", "Email", "Telefono","Nessun Filtro"});
        cbType.setEditable(false);
        cbType.setBackground(new Color(35,35,35));
        cbType.setForeground(Color.WHITE);
        cbType.setRenderer(new CustomComboBoxRenderer());
        cbType.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(35,35,35));

        // Pannello per i componenti a sinistra
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(35,35,35));
        leftPanel.add(lblFilter);
        leftPanel.add(cbType);
        leftPanel.add(txtFilter);
        leftPanel.add(btnFilter);
        topPanel.add(leftPanel, BorderLayout.WEST);

        //Pannello per i componenti di destra
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(35,35,35));
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
        table.setBackground(new Color(35,35,35));
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);

        CustomTableCellRenderer renderer = new CustomTableCellRenderer();
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        //inserimento della table in un scrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        //Aggiungi bordi e titolo
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Password Manager"
        );
        border.setTitleColor(Color.WHITE);
        setBorder(border);

        setPreferredSize (new Dimension(750, 400));
        setBackground(new Color(35,35,35));
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

    private class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c;
            //colonna del campo password
            if (column == 4) {
                //mostra la password in chiaro se la cella è selezionata
                if (table.getSelectedRow() == row && table.getSelectedColumn() == column) {
                    c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
                else{
                    String passwordHided = "•".repeat(String.valueOf(value).length());
                    c = super.getTableCellRendererComponent(table, passwordHided, isSelected, hasFocus, row, column);
                }
            }
            else{
                c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
            c.setForeground(Color.WHITE); // Colore del testo
            c.setBackground(new Color(35, 35, 35)); // Colore di sfondo
            return c;
        }
    }

    public class CustomComboBoxRenderer extends DefaultListCellRenderer {
        private Color backgroundColor = new Color(35, 35, 35);
        private Color foregroundColor = Color.WHITE;
        private Color selectionBackground = new Color(60, 60, 60);
        private Color selectionForeground = Color.WHITE;

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                c.setBackground(selectionBackground);
                c.setForeground(selectionForeground);
            } else {
                c.setBackground(backgroundColor);
                c.setForeground(foregroundColor);
            }
            return c;
        }
    }
}
