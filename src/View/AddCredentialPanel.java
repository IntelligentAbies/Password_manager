package View;

import Utils.IsSecureUtil;
import View.CustomElements.CustomButton;
import View.CustomElements.CustomPasswordField;
import View.CustomElements.CustomTextField;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class AddCredentialPanel extends JPanel {
    protected JLabel lblMessage;
    protected JLabel lblSite;
    protected JLabel lblUsername;
    protected JLabel lblEmail;
    protected JLabel lblPhoneNumber;
    protected JLabel lblPassword;
    protected CustomTextField txtSite;
    protected CustomTextField txtUsername;
    protected CustomTextField txtEmail;
    protected CustomTextField txtPhoneNumber;
    protected CustomPasswordField txtPassword;
    protected CustomButton btnSave;
    protected CustomButton btnShowAll;
    protected CustomButton btnClear;
    protected JToggleButton btnShowPassword;
    protected JButton btnGeneratePassword;
    protected ImageIcon showPasswordIcon;
    protected ImageIcon hidePasswordIcon;
    protected ImageIcon generatePasswordIcon;
    protected JPanel buttonPanel;
    protected FocusListener focusListener=new FocusListener();

    public AddCredentialPanel() {
        //mi carico le icone
        try {
            //Sta roba serve solo per fare in modo che le immagini poi vengono caricate nel binario
            InputStream is = getClass().getResourceAsStream("/discover_light.png");
            showPasswordIcon = new ImageIcon(ImageIO.read(is));
            is = getClass().getResourceAsStream("/uncover_light.png");
            hidePasswordIcon = new ImageIcon(ImageIO.read(is));
            is = getClass().getResourceAsStream("/generate.png");
            generatePasswordIcon = new ImageIcon(ImageIO.read(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        showPasswordIcon= new ImageIcon(showPasswordIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        hidePasswordIcon= new ImageIcon(hidePasswordIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        generatePasswordIcon = new ImageIcon(generatePasswordIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setBackground(new Color(35,35,35));

        GridBagConstraints gbc = new GridBagConstraints();

        //margini di ogni griglia
        gbc.insets = new Insets(5, 5, 5, 5);

        //gli elementi si "strechano" orizontalmente
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //ctichette
        lblMessage = new JLabel("Inserisci Le tue credenziali per salvarle!");
        lblMessage.setForeground(Color.WHITE);
        lblSite = new JLabel("Sito:");
        lblSite.setForeground(Color.WHITE);
        lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblPhoneNumber = new JLabel("PhoneNumber:");
        lblPhoneNumber.setForeground(Color.WHITE);
        lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);

        //campi di testo
        txtSite = new CustomTextField(20);
        txtSite.addActionListener(focusListener);
        txtUsername = new CustomTextField(20);
        txtUsername.addActionListener(focusListener);
        txtEmail = new CustomTextField(20);
        txtEmail.addActionListener(focusListener);
        txtPhoneNumber = new CustomTextField(20);
        txtPhoneNumber.setCaretColor(Color.WHITE);
        txtPhoneNumber.addActionListener(focusListener);
        txtPassword = new CustomPasswordField(20);

        //pulsanti
        btnSave= new CustomButton("Salva");
        btnShowAll = new CustomButton("Visualizza Tutti");
        btnClear = new CustomButton("Cancella");
        btnClear.addActionListener(new ClearListener());
        btnShowPassword = new JToggleButton(showPasswordIcon);
        btnShowPassword.setBorderPainted(false); // Rimuove il bordo
        btnShowPassword.setContentAreaFilled(false); // Rimuove lo sfondo
        btnShowPassword.setFocusPainted(false); // Rimuove La cornice dopo che hai premuto
        btnShowPassword.setMargin(new Insets(0, 0, 0, 0)); // Rimuove i margini
        btnShowPassword.addActionListener(new ShowPasswordListener());
        btnGeneratePassword = new JButton(generatePasswordIcon);
        btnGeneratePassword.setBorderPainted(false); // Rimuove il bordo
        btnGeneratePassword.setContentAreaFilled(false); // Rimuove lo sfondo
        btnGeneratePassword.setFocusPainted(false); // Rimuove La cornice dopo che hai premuto
        btnGeneratePassword.setMargin(new Insets(0, 0, 0, 0)); // Rimuove i margini
        btnGeneratePassword.addActionListener(new GeneratePasswordListener());

        //posizionamento componenti
        gbc.gridx = 0;
        gbc.gridy = 0;
        gridPanel.add(lblMessage, gbc);

        gbc.gridy = 1;
        gridPanel.add(lblSite, gbc);

        gbc.gridy = 2;
        gridPanel.add(lblUsername, gbc);

        gbc.gridy = 3;
        gridPanel.add(lblEmail, gbc);

        gbc.gridy = 4;
        gridPanel.add(lblPhoneNumber, gbc);

        gbc.gridy = 5;
        gridPanel.add(lblPassword, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gridPanel.add(txtSite, gbc);

        gbc.gridy = 2;
        gridPanel.add(txtUsername, gbc);

        gbc.gridy = 3;
        gridPanel.add(txtEmail, gbc);

        gbc.gridy = 4;
        gridPanel.add(txtPhoneNumber, gbc);

        gbc.gridy = 5;
        gridPanel.add(txtPassword, gbc);

        gbc.gridx = 3;
        gridPanel.add(btnShowPassword, gbc);

        gbc.gridx = 4;
        gridPanel.add(btnGeneratePassword, gbc);

        //pannello per i pulsanti
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(35,35,35));
        buttonPanel.add(btnSave);
        buttonPanel.add(btnClear);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gridPanel.add(buttonPanel, gbc);
        add(gridPanel,BorderLayout.CENTER);

        //aggiunta bottone cambio scheda
        JPanel showAllPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        showAllPanel.setBackground(new Color(35,35,35));
        showAllPanel.add(btnShowAll);
        add(showAllPanel,BorderLayout.NORTH);

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Password Manager"
        );
        border.setTitleColor(Color.WHITE);
        setBorder(border);

        setPreferredSize (new Dimension(750, 400));
        setBackground(new Color(35,35,35));
    }

    public void clear(){
        txtSite.setText("");
        txtUsername.setText("");
        txtEmail.setText("");
        txtPhoneNumber.setText("");
        txtPassword.setText("");
    }

    public JButton getBtnShowAll() {
        return btnShowAll;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JTextField getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JTextField getTxtSite() {
        return txtSite;
    }

    public class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clear();
        }
    }

    public class FocusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == txtSite){
                txtUsername.requestFocus();
            }
            else if(e.getSource() == txtUsername){
                txtEmail.requestFocus();
            }
            else if(e.getSource() == txtEmail){
                txtPhoneNumber.requestFocus();
            }
            else if(e.getSource() == txtPhoneNumber){
                txtPassword.requestFocus();
            }
        }
    }

    public class ShowPasswordListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String text = txtPassword.getText();
            if(btnShowPassword.getModel().isSelected()){
                txtPassword.setEchoChar((char) 0);
                btnShowPassword.setIcon(hidePasswordIcon);
            }
            else{
                txtPassword.setEchoChar('•');
                btnShowPassword.setIcon(showPasswordIcon);
            }
            txtPassword.setText(text);
        }
    }

    public class GeneratePasswordListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            txtPassword.setText(IsSecureUtil.generateSecurePassword());
        }
    }
}