package UI;

import Controller.Controller;
import Exceptions.PasswordIsWrong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstLoginPanel extends JPanel {
    protected JLabel label;
    protected JButton btnLogin;
    protected JPasswordField txtPassword;
    protected JPasswordField txtPasswordConfirm;
    protected FirstLoginListener firstLoginListener;

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

        firstLoginListener = new FirstLoginListener();
        btnLogin = new JButton("Login");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.addActionListener(firstLoginListener);
        add(btnLogin);

        // Glue sotto per spingere il gruppo verso il centro
        add(Box.createVerticalGlue());

        // Aggiungi bordi e titolo
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Password Manager"));

        setPreferredSize (new Dimension(850, 400));
    }

    private class FirstLoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String password = txtPassword.getText();
            String passwordConfirm = txtPasswordConfirm.getText();
            if(password.equals(passwordConfirm)) {
                Controller controller = new Controller();
                try {
                    controller.setPassword(password);
                    JOptionPane.showMessageDialog(null,"Password Creata Con Successo : )");

                    //Prendo la reference del mainPanel e quella del suo layout
                    JPanel parent = (JPanel) FirstLoginPanel.this.getParent();
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "TableCredentialPanel");
                } catch (PasswordIsWrong ex) {
                    JOptionPane.showMessageDialog(null,"C'è stato un errore imprevisto : (");
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Le Due Password Non Combaciano : (");
            }
        }
    }
}
