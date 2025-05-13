package UI;

import Controller.Controller;
import Exceptions.PasswordIsWrong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NormalLoginPanel extends FirstLoginPanel{
    private TableCredentialPanel tableCredentialPanel;

    public NormalLoginPanel() {
        super();
        txtPasswordConfirm.setVisible(false);
        btnLogin.removeActionListener(firstLoginListener);
        btnLogin.addActionListener(new LoginListener());
    }

    public void setTableCredentialPanel(TableCredentialPanel tableCredentialPanel) {
        this.tableCredentialPanel = tableCredentialPanel;
    }

    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String password = new String(txtPassword.getPassword());
            Controller controller = new Controller();
            try {
                controller.setPassword(password);
                //Prendo la reference del mainPanel e quella del suo layout
                JPanel parent = (JPanel) NormalLoginPanel.this.getParent();
                CardLayout cl = (CardLayout) parent.getLayout();
                cl.show(parent, "TableCredentialPanel");
                tableCredentialPanel.refreshTable();
            } catch (PasswordIsWrong ex) {
                JOptionPane.showMessageDialog(null,"La password è sbagliata : (");
            }
        }
    }
}
