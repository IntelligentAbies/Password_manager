import Controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Manager by Diego Ferventi");
        try {
            //Sta roba serve solo per fare in modo che le immagini poi vengono caricate nel binario
            InputStream is = Main.class.getResourceAsStream("/shield.png");
            ImageIcon logo = new ImageIcon(ImageIO.read(is));
            Image scaledLogo = logo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            frame.setIconImage(scaledLogo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Controller controller = new Controller();
        frame.getContentPane().add(controller.getMainPanel());
        frame.pack();
        frame.setVisible(true);
    }
}