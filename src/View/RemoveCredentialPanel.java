package View;



import javax.swing.*;


public class RemoveCredentialPanel extends AddCredentialPanel{
    private JButton btnShow;
    public RemoveCredentialPanel() {
        super();
        lblMessage.setText("Inserisci Sito e Username delle credenziali da rimuovere!");
        txtUsername.removeActionListener(focusListener);
        txtEmail.setEditable(false);
        txtEmail.removeActionListener(focusListener);
        txtPhoneNumber.setEditable(false);
        txtPhoneNumber.removeActionListener(focusListener);
        txtPassword.setEditable(false);
        btnSave.setText("Rimuovi");
        btnShow = new JButton("Cerca");
        buttonPanel.add(btnShow);
    }

    public JButton getBtnShow() {
        return btnShow;
    }
}
