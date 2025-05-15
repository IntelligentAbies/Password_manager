package View;
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
        cardLayout = new CardLayout();
        setLayout(cardLayout);

    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public FirstLoginPanel getFirstLoginPanel() {
        return firstLoginPanel;
    }

    public NormalLoginPanel getNormalLoginPanel() {
        return normalLoginPanel;
    }

    public AddCredentialPanel getAddCredentialPanel() {
        return addCredentialPanel;
    }

    public TableCredentialPanel getTableCredentialPanel() {
        return tableCredentialPanel;
    }

    public RemoveCredentialPanel getRemoveCredentialPanel() {
        return removeCredentialPanel;
    }

    public void setFirstLoginPanel(FirstLoginPanel firstLoginPanel) {
        this.firstLoginPanel = firstLoginPanel;
        add(firstLoginPanel, "FirstLoginPanel");
    }

    public void setNormalLoginPanel(NormalLoginPanel normalLoginPanel) {
        this.normalLoginPanel = normalLoginPanel;
        add(normalLoginPanel, "NormalLoginPanel");
    }

    public void setAddCredentialPanel(AddCredentialPanel addCredentialPanel) {
        this.addCredentialPanel = addCredentialPanel;
        add(addCredentialPanel, "AddCredentialPanel");
    }

    public void setTableCredentialPanel(TableCredentialPanel tableCredentialPanel) {
        this.tableCredentialPanel = tableCredentialPanel;
        add(tableCredentialPanel, "TableCredentialPanel");
    }

    public void setRemoveCredentialPanel(RemoveCredentialPanel removeCredentialPanel) {
        this.removeCredentialPanel = removeCredentialPanel;
        add(removeCredentialPanel, "RemoveCredentialPanel");
    }
}