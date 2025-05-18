package View;



import View.CustomElements.CustomButton;

import javax.swing.*;


public class RemoveCredentialPanel extends AddCredentialPanel{
    private CustomButton btnShow;
    public RemoveCredentialPanel() {
        super();
        lblMessage.setText("Inserisci Sito e Username da rimuovere!");
        txtUsername.removeActionListener(focusListener);
        txtEmail.setEditable(false);
        txtEmail.removeActionListener(focusListener);
        txtPhoneNumber.setEditable(false);
        txtPhoneNumber.removeActionListener(focusListener);
        txtPassword.setEditable(false);
        btnSave.setText("Rimuovi");
        btnShow = new CustomButton("Cerca");
        buttonPanel.add(btnShow);
    }

    public JButton getBtnShow() {
        return btnShow;
    }
}
