package View;

import Controller.Controller;
import Exceptions.CredentialDoesntExist;
import Exceptions.PasswordIsWrong;
import Model.Credential;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCredentialPanel extends AddCredentialPanel{
    private JButton btnShow;
    public RemoveCredentialPanel() {
        super();
        this.lblMessage.setText("Inserisci Sito e Username delle credenziali da rimuovere!");
        this.txtEmail.setEditable(false);
        this.txtPhoneNumber.setEditable(false);
        this.txtPassword.setEditable(false);
        this.btnSave.setText("Rimuovi");
        btnShow = new JButton("Cerca");
        btnShow.addActionListener(new FindButtonListener());
        buttonPanel.add(btnShow);
        this.btnSave.removeActionListener(createButtonListener);
        this.btnSave.addActionListener(new RemoveButtonListener());
    }

    private class RemoveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Controller controller = new Controller();
            try {
                controller.removeCredential(txtSite.getText(),txtUsername.getText());
                JOptionPane.showMessageDialog(null,"Credenziali Rimosse con successo!");
                clear();
                tableCredentialPanel.refreshTable();
            } catch (PasswordIsWrong ex) {
                JOptionPane.showMessageDialog(null,"La password è sbagliata : (");
            } catch (CredentialDoesntExist ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }

        }
    }

    private class FindButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Controller controller = new Controller();
            try {
                Credential credential = controller.find(txtSite.getText(),txtUsername.getText());
                txtEmail.setText(credential.getEmail());
                txtPhoneNumber.setText(credential.getPhoneNumber());
                txtPassword.setText(credential.getPassword());
            } catch (CredentialDoesntExist ex) {
                JOptionPane.showMessageDialog(null,"Le credenziali inserite non esistono : (");
            }
        }
    }

}
