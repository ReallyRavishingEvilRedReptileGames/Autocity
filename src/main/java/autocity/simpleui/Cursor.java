package autocity.simpleui;

import autocity.core.World;
import autocity.core.exceptions.TileOutOfBoundsException;

import java.awt.event.KeyEvent;

/**
 * Created by Whiplash on 1/18/2015.
 */
public class Cursor {

    private int x, y = 0;
    private World world;

    public Cursor(World world) {
        this.world = world;
    }

    public void Move(KeyEvent e) {
        try {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (y - 1 < 0) {
                } else {
                    world.getTile(x, y).setSelected();
                    y--;
                    world.getTile(x, y).setSelected();
                }

            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (y + 1 > world.getHeight() - 1) {
                } else {
                    world.getTile(x, y).setSelected();
                    y++;
                    world.getTile(x, y).setSelected();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (x - 1 < 0) {
                } else {
                    world.getTile(x, y).setSelected();
                    x--;
                    world.getTile(x, y).setSelected();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (x + 1 > world.getWidth() - 1) {
                } else {
                    world.getTile(x, y).setSelected();
                    x++;
                    world.getTile(x, y).setSelected();
                }
            }
            System.out.println("X: " + x + " Y: " + y);
        } catch (TileOutOfBoundsException TOBE) {

        }
    }
}
