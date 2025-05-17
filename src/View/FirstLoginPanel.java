package View;

import View.CustomElements.CustomButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class FirstLoginPanel extends JPanel {
    protected JLabel label;
    protected CustomButton btnLogin;
    protected JToggleButton btnShowPassword;
    protected JPasswordField txtPassword;
    protected JPasswordField txtPasswordConfirm;
    protected JLabel lblSecurityLevel;
    protected JPanel labelPasswordPanel;
    protected JPanel passwordInsertionPanel;
    protected ImageIcon showPasswordIcon;
    protected ImageIcon hidePasswordIcon;
    protected FocusListener focusListener = new FocusListener();
    protected JPanel labelPasswordConfirmPanel;
    protected Component spacerBelowSecurityLabel;
    protected Component spacerBelowPasswordConfirmPanel;
    protected JLabel lblPasswordConfirm;

    public FirstLoginPanel() {
        //mi carico le icone
        try {
            //Sta roba serve solo per fare in modo che le immagini poi vengono caricate nel binario
            InputStream is = getClass().getResourceAsStream("/discover_light.png");
            showPasswordIcon = new ImageIcon(ImageIO.read(is));
            is = getClass().getResourceAsStream("/uncover_light.png");
            hidePasswordIcon = new ImageIcon(ImageIO.read(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image scaledShowImage = showPasswordIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        Image scaledHideImage = hidePasswordIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);

        showPasswordIcon= new ImageIcon(scaledShowImage);
        hidePasswordIcon= new ImageIcon(scaledHideImage);

        //Layout in colonna
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Glue sopra per spingere il gruppo verso il centro
        add(Box.createVerticalGlue());

        //Prima label
        label=new JLabel("Crea La Tua Password Per Iniziare!");
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(label.getPreferredSize().width, label.getPreferredSize().height));
        add(label);

        add(Box.createRigidArea(new Dimension(0, 10)));

        //label password
        JPanel labelBoxPassowrd=new JPanel();
        labelBoxPassowrd.setBackground(new Color(35,35,35));
        labelBoxPassowrd.setLayout(new BoxLayout(labelBoxPassowrd, BoxLayout.X_AXIS));

        JLabel lblPassword=new JLabel("Password: ");
        lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblPassword.setForeground(Color.WHITE);
        labelBoxPassowrd.add(lblPassword);
        labelBoxPassowrd.add(Box.createRigidArea(new Dimension(138, 0)));
        add(labelBoxPassowrd);


        //panel contenete primo campo password + bottone di visualizzazione della password
        passwordInsertionPanel=new JPanel();
        passwordInsertionPanel.setBackground(new Color(35,35,35));
        passwordInsertionPanel.setLayout(new BoxLayout(passwordInsertionPanel, BoxLayout.X_AXIS));
        passwordInsertionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordInsertionPanel.add(Box.createRigidArea(new Dimension(22, 0)));

        //primo campo password
        txtPassword = new JPasswordField();
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setBackground(new Color(35,35,35));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPassword.setPreferredSize(new Dimension(200, 20));
        txtPassword.setMaximumSize(new Dimension(200, 20));
        txtPassword.setMinimumSize(new Dimension(200, 20));
        txtPassword.addActionListener(focusListener);
        passwordInsertionPanel.add(txtPassword);

        //bottone di visualizzazione della password
        btnShowPassword = new JToggleButton(showPasswordIcon);
        btnShowPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnShowPassword.setBorderPainted(false); // Rimuove il bordo
        btnShowPassword.setContentAreaFilled(false); // Rimuove lo sfondo
        btnShowPassword.setFocusPainted(false); // Rimuove La cornice dopo che hai premuto
        btnShowPassword.setMargin(new Insets(0, 0, 0, 0)); // Rimuove i margini
        passwordInsertionPanel.add(btnShowPassword);

        add(passwordInsertionPanel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        labelPasswordConfirmPanel= new JPanel();
        labelPasswordConfirmPanel.setBackground(new Color(35,35,35));
        labelPasswordConfirmPanel.setLayout(new BoxLayout(labelPasswordConfirmPanel, BoxLayout.X_AXIS));

        //label password
        lblPasswordConfirm=new JLabel("Conferma: ");
        lblPasswordConfirm.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblPasswordConfirm.setForeground(Color.WHITE);
        labelPasswordConfirmPanel.add(lblPasswordConfirm);
        labelPasswordConfirmPanel.add(Box.createRigidArea(new Dimension(138, 0)));
        add(labelPasswordConfirmPanel);

        //secondo campo password
        txtPasswordConfirm = new JPasswordField();
        txtPasswordConfirm.setCaretColor(Color.WHITE);
        txtPasswordConfirm.setForeground(Color.WHITE);
        txtPasswordConfirm.setBackground(new Color(35,35,35));
        txtPasswordConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPasswordConfirm.setPreferredSize(new Dimension(200, 20));
        txtPasswordConfirm.setMaximumSize(new Dimension(200, 20));
        txtPasswordConfirm.setMinimumSize(new Dimension(200, 20));
        add(txtPasswordConfirm);

        spacerBelowPasswordConfirmPanel= Box.createRigidArea(new Dimension(0, 10));
        add(spacerBelowPasswordConfirmPanel);

        //panel per il livello di sicurezza della password
        labelPasswordPanel = new JPanel();
        labelPasswordPanel.setBackground(new Color(35,35,35));
        labelPasswordPanel.setLayout(new BoxLayout(labelPasswordPanel, BoxLayout.X_AXIS));
        labelPasswordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSecurityIndicator = new JLabel("La Password è: ");
        lblSecurityIndicator.setForeground(Color.WHITE);
        lblSecurityIndicator.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSecurityIndicator.setMaximumSize(new Dimension(lblSecurityIndicator.getPreferredSize().width, lblSecurityIndicator.getPreferredSize().height));

        lblSecurityLevel=new JLabel("Debole");
        lblSecurityLevel.setForeground(Color.RED);
        lblSecurityLevel.setAlignmentX(Component.CENTER_ALIGNMENT);


        labelPasswordPanel.add(lblSecurityIndicator);
        labelPasswordPanel.add(lblSecurityLevel);
        add(labelPasswordPanel);


        //Spazio fisso tra field e bottone
        spacerBelowSecurityLabel=Box.createRigidArea(new Dimension(0, 10));
        add(spacerBelowSecurityLabel);

        btnLogin = new CustomButton("Login");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension d =new Dimension(200, 25);
        btnLogin.setPreferredSize(d);
        btnLogin.setMaximumSize(d);
        btnLogin.setMinimumSize(d);
        add(btnLogin);

        // Glue sotto per spingere il gruppo verso il centro
        add(Box.createVerticalGlue());

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Password Manager"
        );
        border.setTitleColor(Color.WHITE);
        setBorder(border);

        setPreferredSize (new Dimension(850, 400));
        setBackground(new Color(35,35,35));
    }

    public ImageIcon getShowPasswordIcon() {
        return showPasswordIcon;
    }

    public ImageIcon getHidePasswordIcon() {
        return hidePasswordIcon;
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

    public JToggleButton getBtnShowPassword() {
        return btnShowPassword;
    }

    public class FocusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==txtPassword){
                txtPasswordConfirm.requestFocus();
            }
        }
    }
}
