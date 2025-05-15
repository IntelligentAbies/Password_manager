package Controller;

import Exceptions.CredentialDoesntExist;
import Exceptions.CredentialNotValid;
import Exceptions.PasswordIsWrong;
import Model.Credential;
import Model.SetOfCredential;
import Utils.IsSecureUtil;
import View.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener, DocumentListener {
    private SetOfCredential credentials;
    private MainPanel mainPanel;

    public Controller(){
        credentials = new SetOfCredential();
        mainPanel = new MainPanel();
        if(credentials.getIsSetupped()) {
            mainPanel.setNormalLoginPanel(new NormalLoginPanel());
            mainPanel.setFirstLoginPanel(new FirstLoginPanel());
        }
        else{
            mainPanel.setFirstLoginPanel(new FirstLoginPanel());
            mainPanel.setNormalLoginPanel(new NormalLoginPanel());
        }
        mainPanel.setTableCredentialPanel(new TableCredentialPanel());
        mainPanel.setAddCredentialPanel(new AddCredentialPanel());
        mainPanel.setRemoveCredentialPanel(new RemoveCredentialPanel());

        //Listener per la schermata di registrazione
        mainPanel.getFirstLoginPanel().getBtnLogin().addActionListener(this);
        mainPanel.getFirstLoginPanel().getTxtPassword().getDocument().addDocumentListener(this);
        mainPanel.getFirstLoginPanel().getBtnShowPassword().addActionListener(this);

        //Listener per la schermata di login
        mainPanel.getNormalLoginPanel().getBtnLogin().addActionListener(this);
        mainPanel.getNormalLoginPanel().getBtnShowPassword().addActionListener(this);

        //Listener per la schermata di visualizzazione
        mainPanel.getTableCredentialPanel().getBtnCreate().addActionListener(this);
        mainPanel.getTableCredentialPanel().getBtnRemove().addActionListener(this);
        mainPanel.getTableCredentialPanel().getBtnFilter().addActionListener(this);

        //Listener per la schermata di aggiunta credenziale
        mainPanel.getAddCredentialPanel().getBtnSave().addActionListener(this);
        mainPanel.getAddCredentialPanel().getBtnShowAll().addActionListener(this);

        //Listener per la schermata di rimozione delle credenziali
        mainPanel.getRemoveCredentialPanel().getBtnSave().addActionListener(this);
        mainPanel.getRemoveCredentialPanel().getBtnShowAll().addActionListener(this);
        mainPanel.getRemoveCredentialPanel().getBtnShow().addActionListener(this);


    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void refreshTable() {
        DefaultTableModel tableModel = mainPanel.getTableCredentialPanel().getTableModel();
        tableModel.setRowCount(0);
        try {
            List<Credential>c = credentials.getCredentials();
            for (Credential credential : c) {
                tableModel.addRow(new Object[]{credential.getSite(), credential.getUsername(), credential.getEmail(),credential.getPhoneNumber(), credential.getPassword()});
            }
        } catch (PasswordIsWrong e) {
            JOptionPane.showMessageDialog(null,"Qualcosa è andato storto : (");
        }
    }

    public void refreshTable(List<Credential> credentials){
        DefaultTableModel tableModel = mainPanel.getTableCredentialPanel().getTableModel();
        tableModel.setRowCount(0);
        for (Credential credential : credentials) {
            tableModel.addRow(new Object[]{credential.getSite(), credential.getUsername(), credential.getEmail(),credential.getPhoneNumber(), credential.getPassword()});
        }
    }

    public void updatePasswordSecurityLevel(){
        int securityLevel= IsSecureUtil.securityLevel(mainPanel.getFirstLoginPanel().getTxtPassword().getText());
        JLabel lblSecurityLevel = mainPanel.getFirstLoginPanel().getLblSecurityLevel();
        switch(securityLevel){
            case 0:
                lblSecurityLevel.setText("Debole");
                lblSecurityLevel.setForeground(Color.red);
                break;
            case 1:
                lblSecurityLevel.setText("Media");
                lblSecurityLevel.setForeground(new Color(218, 165, 32));
                break;
            case 2:
                lblSecurityLevel.setText("Buona");
                lblSecurityLevel.setForeground(new Color(0, 100, 0));
                break;
            case 3:
                lblSecurityLevel.setText("Forte");
                lblSecurityLevel.setForeground(new Color(50, 200, 50));
                break;
        }
        lblSecurityLevel.setMaximumSize(new Dimension(lblSecurityLevel.getPreferredSize().width, lblSecurityLevel.getPreferredSize().height));

    }

    private void register(){
        String password = mainPanel.getFirstLoginPanel().getTxtPassword().getText();
        String passwordConfirm = mainPanel.getFirstLoginPanel().getTxtPasswordConfirm().getText();
        if (!password.equals(passwordConfirm)) {
            JOptionPane.showMessageDialog(null,"Le Due Password Non Combaciano : (");
        }
        else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Il campo della password non deve essere vuoto : (");
        }
        else{
            try{
                credentials.setPassword(password);
                JOptionPane.showMessageDialog(null,"Password Creata Con Successo : )");
                mainPanel.getCardLayout().show(mainPanel,"TableCredentialPanel");
            }catch (PasswordIsWrong passwordWrong){
                JOptionPane.showMessageDialog(null,"C'è stato un errore imprevisto : (");
            }
        }

    }

    private void login(){
        String password = new String(mainPanel.getNormalLoginPanel().getTxtPassword().getText());
        try{
            credentials.setPassword(password);
            refreshTable();
            mainPanel.getCardLayout().show(mainPanel,"TableCredentialPanel");
        } catch (PasswordIsWrong e) {
            JOptionPane.showMessageDialog(null,"La password è sbagliata : (");
        }
    }

    public void filter(){
        String filter = mainPanel.getTableCredentialPanel().getTxtFilter().getText();
        String type = mainPanel.getTableCredentialPanel().getCbType().getSelectedItem().toString();
        List<Credential> creds = new ArrayList<>();
        //Intellj mi consigliava questa sintassi e la trovavo molto carina
        creds = switch (type) {
            case "Sito" -> credentials.find(0, filter);
            case "Username" -> credentials.find(1, filter);
            case "Email" -> credentials.find(2, filter);
            case "Telefono" -> credentials.find(3, filter);
            case "Nessun Filtro" -> credentials.find(4, filter);
            default -> creds;
        };
        refreshTable(creds);
    }

    public void addCredential(){
        Credential credential=new Credential();
        credential.setSite(mainPanel.getAddCredentialPanel().getTxtSite().getText());
        credential.setUsername(mainPanel.getAddCredentialPanel().getTxtUsername().getText());
        credential.setEmail(mainPanel.getAddCredentialPanel().getTxtEmail().getText());
        credential.setPhoneNumber(mainPanel.getAddCredentialPanel().getTxtPhoneNumber().getText());
        credential.setPassword(mainPanel.getAddCredentialPanel().getTxtPassword().getText());
        try {
            credentials.add(credential);
            JOptionPane.showMessageDialog(null,"Credenziali Aggiunte con successo : )");
            mainPanel.getAddCredentialPanel().clear();
            refreshTable();
        } catch (PasswordIsWrong e) {
            JOptionPane.showMessageDialog(null,"Qualcosa è andato storto : (");
        }catch (CredentialNotValid ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }

    public void findCredential(){
        try {
            Credential credential = credentials.find(mainPanel.getRemoveCredentialPanel().getTxtSite().getText(), mainPanel.getRemoveCredentialPanel().getTxtUsername().getText());
            mainPanel.getRemoveCredentialPanel().getTxtEmail().setText(credential.getEmail());
            mainPanel.getRemoveCredentialPanel().getTxtPhoneNumber().setText(credential.getPhoneNumber());
            mainPanel.getRemoveCredentialPanel().getTxtPassword().setText(credential.getPassword());
        }catch (CredentialNotValid ex){
            JOptionPane.showMessageDialog(null,"Le credenziali inserite non esistono : (");
        }
    }

    public void removeCredential(){
        try {
            credentials.remove(mainPanel.getRemoveCredentialPanel().getTxtSite().getText(), mainPanel.getRemoveCredentialPanel().getTxtUsername().getText());
            JOptionPane.showMessageDialog(null,"Credenziali Rimosse con successo!");
            mainPanel.getRemoveCredentialPanel().clear();
            refreshTable();
        } catch (PasswordIsWrong ex) {
            JOptionPane.showMessageDialog(null,"Qualcosa è andato storto : (");
        } catch (CredentialDoesntExist ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }


    public void showPassword(JToggleButton btnShowPassword,JPasswordField txtPassword){
        String text = txtPassword.getText();
        if(btnShowPassword.getModel().isSelected()){
            txtPassword.setEchoChar((char) 0);
            btnShowPassword.setIcon(mainPanel.getFirstLoginPanel().getHidePasswordIcon());
        }
        else{
            txtPassword.setEchoChar('•');
            btnShowPassword.setIcon(mainPanel.getFirstLoginPanel().getShowPasswordIcon());
        }
        txtPassword.setText(text);
    }

    //Actionlistener
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==mainPanel.getFirstLoginPanel().getBtnLogin()){
            register();
        }
        else if(e.getSource()==mainPanel.getFirstLoginPanel().getBtnShowPassword()){
            JToggleButton btnShowPassword = mainPanel.getFirstLoginPanel().getBtnShowPassword();
            JPasswordField txtPassword = mainPanel.getFirstLoginPanel().getTxtPassword();
            showPassword(btnShowPassword,txtPassword);
        }
        else if(e.getSource()==mainPanel.getNormalLoginPanel().getBtnShowPassword()){
            JToggleButton btnShowPassword = mainPanel.getNormalLoginPanel().getBtnShowPassword();
            JPasswordField txtPassword = mainPanel.getNormalLoginPanel().getTxtPassword();
            showPassword(btnShowPassword,txtPassword);
        }
        else if(e.getSource()==mainPanel.getNormalLoginPanel().getBtnLogin()){
            login();
        }
        else if(e.getSource()==mainPanel.getTableCredentialPanel().getBtnFilter()){
            filter();
        }
        else if(e.getSource()==mainPanel.getTableCredentialPanel().getBtnCreate()){
            mainPanel.getCardLayout().show(mainPanel,"AddCredentialPanel");
        }
        else if(e.getSource()==mainPanel.getTableCredentialPanel().getBtnRemove()) {
            mainPanel.getCardLayout().show(mainPanel,"RemoveCredentialPanel");
        }
        else if(e.getSource()==mainPanel.getAddCredentialPanel().getBtnSave()){
            addCredential();
        }
        else if(e.getSource()==mainPanel.getAddCredentialPanel().getBtnShowAll()||e.getSource()==mainPanel.getRemoveCredentialPanel().getBtnShowAll()){
            mainPanel.getCardLayout().show(mainPanel,"TableCredentialPanel");
        }
        else if(e.getSource()==mainPanel.getRemoveCredentialPanel().getBtnShow()){
            findCredential();
        }
        else if(e.getSource()==mainPanel.getRemoveCredentialPanel().getBtnSave()){
            removeCredential();
        }
    }


    //DocumentListener
    @Override
    public void insertUpdate(DocumentEvent e) {
        updatePasswordSecurityLevel();
    }

    //DocumentListener
    @Override
    public void removeUpdate(DocumentEvent e) {
        updatePasswordSecurityLevel();
    }

    //DocumentListener
    @Override
    public void changedUpdate(DocumentEvent e) {
        updatePasswordSecurityLevel();
    }


}
