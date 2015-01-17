package autocity.simpleui;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UIFrame extends JFrame implements MouseListener {
    //private JTextArea textArea;
    private JTextPane textPane;
    private DefaultStyledDocument styledDocument;
    private SimpleUI simpleUI;

    public UIFrame(SimpleUI simpleUI) {
        super("Autocity SimpleUI");
        this.textPane = new JTextPane();
        this.styledDocument = new DefaultStyledDocument();
        this.textPane.setFont(new Font("Consolas", Font.PLAIN, 10));
        this.simpleUI = simpleUI;
        //this.textArea = new JTextArea();
        //this.textArea.setFont(new Font("Consolas", Font.PLAIN, 10));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Restart");

        menu.addMouseListener(this);

        menuBar.add(menu);

        this.add(menuBar, BorderLayout.NORTH);
        this.add(textPane, BorderLayout.CENTER);
        this.setSize(800, 940);
        this.setVisible(true);
    }

    public void setText(String text) {
        //textArea.setText(text);
        textPane.setText(text);
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
