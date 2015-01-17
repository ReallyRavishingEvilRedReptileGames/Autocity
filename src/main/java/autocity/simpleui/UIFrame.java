package autocity.simpleui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UIFrame extends JFrame implements MouseListener {
    private JTextArea textArea;
    private SimpleUI simpleUI;

    public UIFrame(SimpleUI simpleUI) {
        super("AutoCity SimpleUI");

        this.simpleUI = simpleUI;
        this.textArea = new JTextArea();
        this.textArea.setFont(new Font("Consolas", Font.PLAIN, 10));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Restart");

        menu.addMouseListener(this);

        menuBar.add(menu);

        this.add(menuBar, BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);
        this.setSize(800, 940);
        this.setVisible(true);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // There's only one option for now...
        this.simpleUI.getGame().restartGame();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
