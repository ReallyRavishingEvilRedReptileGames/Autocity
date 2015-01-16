package autocity.simpleui;

import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame {
    private JTextArea textArea;

    public UIFrame() {
        super("AutoCity SimpleUI");
        textArea = new JTextArea();

        textArea.setFont(new Font("Consolas", Font.PLAIN, 10));

        this.add(textArea);
        this.setSize(800, 920);
        this.setVisible(true);
    }

    public void setText(String text) {
        textArea.setText(text);
    }
}
