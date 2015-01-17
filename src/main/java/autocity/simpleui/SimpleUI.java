package autocity.simpleui;

import autocity.core.Game;
import autocity.core.Tile;
import autocity.core.World;
import autocity.core.exceptions.TileOutOfBoundsException;

/**
 * Warning: only supports Windows because Consolas
 */
public class SimpleUI extends Thread {
    private boolean isRunning = true;
    private long lastloop = System.nanoTime();
    private double delta = 0;
    private int targetfps = 10;
    private double targettime = 2 / (double) targetfps;

    private Game game;
    private UIFrame uiFrame;

    public SimpleUI(Game game) {
        this.game = game;
        this.uiFrame = new UIFrame(this);
    }

    public void run() {
        this.main();
    }

    public Game getGame() {
        return this.game;
    }

    private void main() {
        while (isRunning) {
            long now = System.nanoTime();
            long updateLength = now - lastloop;
            delta += ((double) updateLength / 1000000000);
            lastloop = now;

            if (delta >= targettime) {
                // Do stuff
                try {
                    this.redraw();
                } catch (NullPointerException e) {
                    System.out.println("Caught null pointer exception");
                }

                delta = 0;
            }
        }
    }

    private void redraw() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getHeaderText());
        sb.append(this.getMapText());

        this.uiFrame.setText(sb.toString());
    }

    private StringBuffer getHeaderText() {
        World world = this.game.getWorld();
        int height = world.getHeight();
        int width = world.getWidth();

        StringBuffer sb = new StringBuffer();

        sb.append(String.format("Rrerr~ Map Format: %dx%d, Number of settlements: %d", width, height, world.getSettlements().size()));

        sb.append('\n');
        sb.append('\n');

        return sb;
    }

    private StringBuffer getMapText() {
        World world = this.game.getWorld();
        int height = world.getHeight();
        int width = world.getWidth();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                try {
                    Tile tile = world.getTile(i, j);

                    sb.append(tile.getCharacter());
                } catch (TileOutOfBoundsException e) {
                    //not going to happen but we'll specify a case for this anyway.
                    sb.append('X');
                }

                sb.append(' ');
            }
            sb.append('\n');
        }

        return sb;
    }
}
