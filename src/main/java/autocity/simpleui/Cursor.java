package autocity.simpleui;

import java.awt.event.KeyEvent;

/**
 * Created by Whiplash on 1/18/2015.
 */
public class Cursor {

    int x, y = 0;

    //TODO Make this thing work

    public static void Move(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("We went up!");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("We went down!");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("We went left!");
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("We went right!");
        }
    }
}
