package autocity.simpleui;

import autocity.core.Devmode;

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
    private JTextField devConsole;
    private Devmode dev;

    public UIFrame(SimpleUI simpleUI, Cursor cursor, Devmode dev) {
        super("Autocity SimpleUI");
        this.textPane = new JTextPane();
        this.styledDocument = new DefaultStyledDocument();
        this.textPane.setFont(new Font("Consolas", Font.PLAIN, 7));
        this.simpleUI = simpleUI;
        this.cursor = cursor;
        this.dev = dev;
        this.devConsole = new JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Restart");

        menu.addMouseListener(this);
        menuBar.add(menu);

        this.addKeyListener(this);
        this.devConsole.addKeyListener(this);
        this.textPane.setFocusable(false);

        JMenuBar statusBar = new JMenuBar();
        this.status = new JMenu("Status");
        this.status.setFocusable(false);

        statusBar.add(this.status);

        this.status.setEnabled(false);

        JPanel jp = new JPanel();

        jp.setLayout(new GridLayout(0, 2));
        jp.add(statusBar);
        jp.add(devConsole);
        devConsole.setVisible(false);
        devConsole.setEnabled(false);

        this.add(menuBar, BorderLayout.NORTH);
        this.add(textPane, BorderLayout.CENTER);
        this.add(jp, BorderLayout.SOUTH);
        this.setSize(1280, 900);
        this.setVisible(true);
        this.requestFocus();
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Pass text to Devmode class.
            dev.commandLookup(devConsole.getText());
            devConsole.setText("");
        } else if (e.getKeyChar() == '`') {
            devConsole.setVisible(!devConsole.isVisible());
            devConsole.setEnabled(!devConsole.isEnabled());
            this.requestFocus();
        } else {
            cursor.Move(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
