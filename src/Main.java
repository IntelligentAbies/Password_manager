import Controller.Controller;
import View.MainPanel;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Manager by Diego Ferventi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Controller controller = new Controller();
        frame.getContentPane().add(controller.getMainPanel());
        frame.pack();
        frame.setVisible(true);
    }
}