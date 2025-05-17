package View;


import java.awt.*;

public class NormalLoginPanel extends FirstLoginPanel{
    public NormalLoginPanel() {
        super();
        label.setText("Inserisci la password per accedere!");
        label.setMaximumSize(new Dimension(label.getPreferredSize().width, label.getPreferredSize().height));
        txtPasswordConfirm.setVisible(false);
        labelPasswordConfirmPanel.setVisible(false);
        labelPasswordPanel.setVisible(false);
        spacerBelowSecurityLabel.setVisible(false);
        spacerBelowPasswordConfirmPanel.setVisible(false);
        txtPassword.removeActionListener(focusListener);
    }
}
