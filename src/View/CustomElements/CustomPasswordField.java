package View.CustomElements;

import javax.swing.*;
import java.awt.*;

public class CustomPasswordField extends JPasswordField {
    public CustomPasswordField() {
        setCaretColor(Color.WHITE);
        setForeground(Color.WHITE);
        setBackground(new Color(35,35,35));
    }
    public CustomPasswordField(int columns) {
        super(columns);
        setCaretColor(Color.WHITE);
        setForeground(Color.WHITE);
        setBackground(new Color(35,35,35));
    }
}
