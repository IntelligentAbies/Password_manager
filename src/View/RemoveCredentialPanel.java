package View;



import javax.swing.*;


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
