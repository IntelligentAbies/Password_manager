package View;

import Controller.Controller;
import Exceptions.PasswordIsWrong;
import Utils.IsSecureUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstLoginPanel extends JPanel {
    protected JLabel label;
    protected JButton btnLogin;
    protected JPasswordField txtPassword;
    protected JPasswordField txtPasswordConfirm;
    protected JLabel lblSecurityLevel;

    public FirstLoginPanel() {
        //Layout in colonna
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Glue sopra per spingere il gruppo verso il centro
        add(Box.createVerticalGlue());

        label=new JLabel("Crea La Tua Password Per Iniziare!");
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


        txtPasswordConfirm = new JPasswordField();
        txtPasswordConfirm.setMaximumSize(new Dimension(300, 30)); // fissa larghezza massima
        txtPasswordConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(txtPasswordConfirm);

        //Spazio fisso tra field e bottone
        add(Box.createRigidArea(new Dimension(0, 10)));

        lblSecurityLevel=new JLabel("La Password è: ");
        lblSecurityLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSecurityLevel.setMaximumSize(new Dimension(lblSecurityLevel.getPreferredSize().width, lblSecurityLevel.getPreferredSize().height));
        add(lblSecurityLevel);

        //Spazio fisso tra field e bottone
        add(Box.createRigidArea(new Dimension(0, 10)));

        btnLogin = new JButton("Login");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(btnLogin);

        // Glue sotto per spingere il gruppo verso il centro
        add(Box.createVerticalGlue());

        // Aggiungi bordi e titolo
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Password Manager"));

        setPreferredSize (new Dimension(850, 400));
    }

    public JLabel getLabel() {
        return label;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JPasswordField getTxtPasswordConfirm() {
        return txtPasswordConfirm;
    }

    public JLabel getLblSecurityLevel() {
        return lblSecurityLevel;
    }
}
