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
        buttonPanel.add(btnShow);
    }

    public JButton getBtnShow() {
        return btnShow;
    }
}
