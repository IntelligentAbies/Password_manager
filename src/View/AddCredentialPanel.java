package View;

import View.CustomElements.CustomButton;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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
    protected CustomButton btnSave;
    protected CustomButton btnShowAll;
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
        lblMessage.setForeground(Color.WHITE);
        lblSite = new JLabel("Sito:");
        lblSite.setForeground(Color.WHITE);
        lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblPhoneNumber = new JLabel("PhoneNumber:");
        lblPhoneNumber.setForeground(Color.WHITE);
        lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);

        // Campi di testo
        txtSite = new JTextField(20);
        txtSite.setCaretColor(Color.WHITE);
        txtSite.setForeground(Color.WHITE);
        txtSite.setBackground(new Color(35,35,35));
        txtSite.addActionListener(focusListener);
        txtUsername = new JTextField(20);
        txtUsername.setCaretColor(Color.WHITE);
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setBackground(new Color(35,35,35));
        txtUsername.addActionListener(focusListener);
        txtEmail = new JTextField(20);
        txtEmail.setCaretColor(Color.WHITE);
        txtEmail.setForeground(Color.WHITE);
        txtEmail.setBackground(new Color(35,35,35));
        txtEmail.addActionListener(focusListener);
        txtPhoneNumber = new JTextField(20);
        txtPhoneNumber.setCaretColor(Color.WHITE);
        txtPhoneNumber.setForeground(Color.WHITE);
        txtPhoneNumber.setBackground(new Color(35,35,35));
        txtPhoneNumber.addActionListener(focusListener);
        txtPassword = new JTextField(20);
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setBackground(new Color(35,35,35));

        // Pulsanti
        btnSave= new CustomButton("Salva");
        btnShowAll = new CustomButton("Visualizza Tutti");


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
        buttonPanel.setBackground(new Color(35,35,35));
        buttonPanel.add(btnSave);
        buttonPanel.add(btnShowAll);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        add(Box.createVerticalGlue());

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Password Manager"
        );
        border.setTitleColor(Color.WHITE);
        setBorder(border);

        setPreferredSize (new Dimension(850, 400));
        setBackground(new Color(35,35,35));
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