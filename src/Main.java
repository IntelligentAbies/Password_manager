import UI.MainPanel;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainPanel());
        frame.pack();
        frame.setVisible(true);
    }
}