package View.CustomElements;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    public CustomTextField() {
        init();
    }
    public CustomTextField(int columns) {
        super(columns);
        init();
    }
    private void init(){
        setCaretColor(Color.WHITE);
        setForeground(Color.WHITE);
        setBackground(new Color(35,35,35));
    }
}
