package UI;

import Controller.Controller;
import Exceptions.CredentialNotValid;
import Exceptions.PasswordIsWrong;
import Model.Credential;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCredentialPanel extends JPanel {
    protected TableCredentialPanel tableCredentialPanel;
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
    protected CreateButtonListener createButtonListener;

    public AddCredentialPanel() {
        // Configura il layout del pannello
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Margini di ogni griglia
        gbc.insets = new Insets(5, 5, 5, 5);

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
        txtUsername = new JTextField(20);
        txtEmail = new JTextField(20);
        txtPhoneNumber = new JTextField(20);
        txtPassword = new JTextField(20);

        // Pulsanti
        createButtonListener = new CreateButtonListener();
        btnSave= new JButton("Salva");
        btnSave.addActionListener(createButtonListener);
        btnShowAll = new JButton("Visualizza Tutti");
        btnShowAll.addActionListener(new ShowAllButtonListener());


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

    //serve per fare il refresh della tabella
    public void setTableCredentialPanel(TableCredentialPanel tableCredentialPanel) {
        this.tableCredentialPanel = tableCredentialPanel;
    }

    public class ShowAllButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Prendo la reference del mainPanel e quella del suo layout
            JPanel parent = (JPanel) AddCredentialPanel.this.getParent();
            CardLayout cl = (CardLayout) parent.getLayout();
            cl.show(parent, "TableCredentialPanel");
        }
    }

    public class CreateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("test");
            Controller controller=new Controller();
            Credential credential=new Credential();
            credential.setSite(txtSite.getText());
            credential.setUsername(txtUsername.getText());
            credential.setEmail(txtEmail.getText());
            credential.setPhoneNumber(txtPhoneNumber.getText());
            credential.setPassword(txtPassword.getText());
            try {
                controller.addCredential(credential);
                JOptionPane.showMessageDialog(null,"Credenziali Aggiunte con successo : )");
                clear();
                tableCredentialPanel.refreshTable();
            } catch (PasswordIsWrong ex) {
                JOptionPane.showMessageDialog(null,"La password è sbagliata : (");
            }catch (CredentialNotValid ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }
    }
}