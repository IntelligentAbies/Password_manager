package View.CustomElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    private Color defaultColor = new Color(230,230,230);
    private Color hoverColor = new Color(200,200,200);
    public CustomButton() {
        super();
        init();
    }
    public CustomButton(String text) {
        super(text);
        init();
    }

    private void init(){
        setBackground(defaultColor);
        addMouseListener(new MouseListener());
        setBorderPainted(false);
        setOpaque(true);
    }
    public class MouseListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent evt) {
            setBackground(hoverColor);
        }

        @Override
        public void mouseExited(MouseEvent evt) {
            setBackground(defaultColor);
        }
    }
}
