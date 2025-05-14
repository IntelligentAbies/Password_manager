package View;



import javax.swing.*;

import java.awt.*;


public class FirstLoginPanel extends JPanel {
    protected JLabel label;
    protected JButton btnLogin;
    protected JPasswordField txtPassword;
    protected JPasswordField txtPasswordConfirm;
    protected JLabel lblSecurityLevel;
    protected JPanel labelPanel;

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

        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
        labelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSecurityIndicator = new JLabel("La Password è: ");
        lblSecurityIndicator.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSecurityIndicator.setMaximumSize(new Dimension(lblSecurityIndicator.getPreferredSize().width, lblSecurityIndicator.getPreferredSize().height));

        lblSecurityLevel=new JLabel("Debole");
        lblSecurityLevel.setForeground(Color.RED);
        lblSecurityLevel.setAlignmentX(Component.CENTER_ALIGNMENT);


        labelPanel.add(lblSecurityIndicator);
        labelPanel.add(lblSecurityLevel);
        add(labelPanel);
        //add(lblSecurityLevel);

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
