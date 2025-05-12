package UI;

import Controller.Controller;
import Exceptions.CredentialNotValid;
import Exceptions.PasswordIsWrong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NormalLoginPanel extends JPanel {
    private JLabel label;
    private JButton btnLogin;
    private JPasswordField txtPassword;
    private TableCredentialPanel tableCredentialPanel;

    public NormalLoginPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Glue sopra per spingere il gruppo verso il centro
        add(Box.createVerticalGlue());

        label=new JLabel("Inserisci la password per accedere!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(label.getPreferredSize().width, label.getPreferredSize().height));
        add(label);

        add(Box.createRigidArea(new Dimension(0, 10)));

        txtPassword = new JPasswordField();
        txtPassword.setMaximumSize(new Dimension(300, 30)); // fissa larghezza massima
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(txtPassword);

        //Spazio fisso tra field e bottone
        add(Box.createRigidArea(new Dimension(0, 10)));

        btnLogin = new JButton("Login");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.addActionListener(new LoginListener());
        add(btnLogin);

        //Glue sotto per spingere il gruppo verso il centro
        add(Box.createVerticalGlue());

        //Aggiungi bordi e titolo
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Password Manager"));

        setPreferredSize (new Dimension(850, 400));
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
