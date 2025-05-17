package View.CustomElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    private Color defaultColor = new Color(230,230,230);
    private Color hoverColor = new Color(200,200,200);
    private Color clickColor = new Color(180,180,180);
    public CustomButton() {
        super();
        init();
    }
    public CustomButton(String text) {
        super(text);
        init();
    }

    private void init(){
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    //sono impazzito per trovare sta roba
    @Override
    protected void paintComponent(Graphics g) {
        Color bg;
        if (getModel().isPressed()) {
            bg = clickColor;
        } else if (getModel().isRollover()) {
            bg = hoverColor;
        } else {
            bg = defaultColor;
        }
        g.setColor(bg);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
