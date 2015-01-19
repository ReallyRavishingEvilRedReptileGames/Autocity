package autocity.simpleui;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UIFrame extends JFrame implements MouseListener, KeyListener {
    //private JTextArea textArea;
    private JTextPane textPane;
    private DefaultStyledDocument styledDocument;
    private SimpleUI simpleUI;
    private JMenu status;
    private Cursor cursor;

    public UIFrame(SimpleUI simpleUI, Cursor cursor) {
        super("Autocity SimpleUI");
        this.textPane = new JTextPane();
        this.styledDocument = new DefaultStyledDocument();
        this.textPane.setFont(new Font("Consolas", Font.PLAIN, 7));
        this.simpleUI = simpleUI;
        this.cursor = cursor;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Restart");

        menu.addMouseListener(this);
        menuBar.add(menu);

        this.textPane.addKeyListener(this);

        JMenuBar statusBar = new JMenuBar();
        this.status = new JMenu("Status");

        statusBar.add(this.status);

        this.status.setEnabled(false);

        this.add(menuBar, BorderLayout.NORTH);
        this.add(textPane, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.SOUTH);
        this.setSize(1280, 900);
        this.setVisible(true);
    }

    public void setStatusText(String text) {
        this.status.setText(text);
    }

    public void setText(String text) {
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        cursor.Move(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
