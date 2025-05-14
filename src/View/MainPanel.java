package View;
import Controller.Controller;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel {
    private CardLayout cardLayout;
    private FirstLoginPanel firstLoginPanel;
    private NormalLoginPanel normalLoginPanel;
    private AddCredentialPanel addCredentialPanel;
    private TableCredentialPanel tableCredentialPanel;
    private RemoveCredentialPanel removeCredentialPanel;
    public MainPanel() {
        Controller controller = new Controller();
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        firstLoginPanel = new FirstLoginPanel();
        normalLoginPanel = new NormalLoginPanel();
        addCredentialPanel = new AddCredentialPanel();
        tableCredentialPanel = new TableCredentialPanel();
        removeCredentialPanel = new RemoveCredentialPanel();
        addCredentialPanel.setTableCredentialPanel(tableCredentialPanel);
        removeCredentialPanel.setTableCredentialPanel(tableCredentialPanel);
        normalLoginPanel.setTableCredentialPanel(tableCredentialPanel);


        if(controller.neverStarted()){
            add(firstLoginPanel,"FirstLoginPanel");
            add(normalLoginPanel,"NormalLoginPanel");
        }
        else{
            add(normalLoginPanel,"NormalLoginPanel");
            add(firstLoginPanel,"FirstLoginPanel");
        }
        add(removeCredentialPanel,"RemoveCredentialPanel");
        add(addCredentialPanel,"AddCredentialPanel");
        add(tableCredentialPanel,"TableCredentialPanel");
    }
}