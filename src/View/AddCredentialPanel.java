package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCredentialPanel extends JPanel {
    protected JLabel lblMessage;
    protected JLabel lblSite;
    protected JLabel lblUsername;
    protected JLabel lblEmail;
    protected JLabel lblPhoneNumber;
    protected JLabel lblPassword;
    protected JTextField txtSite;
    protected JTextField txtUsername;
    protected JTextField txtEmail;
    protected JTextField txtPhoneNumber;
    protected JTextField txtPassword;
    protected JButton btnSave;
    protected JButton btnShowAll;
    protected JPanel buttonPanel;
    protected FocusListener focusListener=new FocusListener();

    public AddCredentialPanel() {
        // Configura il layout del pannello
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Margini di ogni griglia
        gbc.insets = new Insets(5, 5, 5, 5);

        //gli elementi si "strechano" orizontalmente
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etichette
        lblMessage = new JLabel("Inserisci Le tue credenziali per salvarle!");
        lblSite = new JLabel("Sito:");
        lblUsername = new JLabel("Username:");
        lblEmail = new JLabel("Email:");
        lblPhoneNumber = new JLabel("PhoneNumber:");
        lblPassword = new JLabel("Password:");

        // Campi di testo
        txtSite = new JTextField(20);
        txtSite.addActionListener(focusListener);
        txtUsername = new JTextField(20);
        txtUsername.addActionListener(focusListener);
        txtEmail = new JTextField(20);
        txtEmail.addActionListener(focusListener);
        txtPhoneNumber = new JTextField(20);
        txtPhoneNumber.addActionListener(focusListener);
        txtPassword = new JTextField(20);

        // Pulsanti
        btnSave= new JButton("Salva");
        btnShowAll = new JButton("Visualizza Tutti");


        // Posizionamento componenti
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblMessage, gbc);

        gbc.gridy = 1;
        add(lblSite, gbc);

        gbc.gridy = 2;
        add(lblUsername, gbc);

        gbc.gridy = 3;
        add(lblEmail, gbc);

        gbc.gridy = 4;
        add(lblPhoneNumber, gbc);

        gbc.gridy = 5;
        add(lblPassword, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(txtSite, gbc);

        gbc.gridy = 2;
        add(txtUsername, gbc);

        gbc.gridy = 3;
        add(txtEmail, gbc);

        gbc.gridy = 4;
        add(txtPhoneNumber, gbc);

        gbc.gridy = 5;
        add(txtPassword, gbc);

        // Pannello per i pulsanti
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnSave);
        buttonPanel.add(btnShowAll);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Aggiungi bordi e titolo
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Password Manager"));
        setPreferredSize (new Dimension(850, 400));
    }

    public void clear(){
        txtSite.setText("");
        txtUsername.setText("");
        txtEmail.setText("");
        txtPhoneNumber.setText("");
        txtPassword.setText("");
    }

    public JButton getBtnShowAll() {
        return btnShowAll;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JTextField getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JTextField getTxtSite() {
        return txtSite;
    }

    public class FocusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == txtSite){
                txtUsername.requestFocus();
            }
            else if(e.getSource() == txtUsername){
                txtEmail.requestFocus();
            }
            else if(e.getSource() == txtEmail){
                txtPhoneNumber.requestFocus();
            }
            else if(e.getSource() == txtPhoneNumber){
                txtPassword.requestFocus();
            }
        }
    }
}