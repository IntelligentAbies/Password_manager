package View;

import Controller.Controller;
import Exceptions.PasswordIsWrong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NormalLoginPanel extends FirstLoginPanel{
    public NormalLoginPanel() {
        super();
        label.setText("Inserisci la password per accedere!");
        label.setMaximumSize(new Dimension(label.getPreferredSize().width, label.getPreferredSize().height));
        txtPasswordConfirm.setVisible(false);
        labelPanel.setVisible(false);
    }
}
