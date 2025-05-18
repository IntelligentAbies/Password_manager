package View.CustomElements;

import javax.swing.*;
import java.awt.*;

public class CustomPasswordField extends JPasswordField {
    private Dimension dim = new Dimension(200, 20);
    public CustomPasswordField() {
        setCaretColor(Color.WHITE);
        setForeground(Color.WHITE);
        setBackground(new Color(35,35,35));
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
    }
}
