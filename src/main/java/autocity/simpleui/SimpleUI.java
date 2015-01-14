package autocity.simpleui;

import autocity.core.Game;
import autocity.core.Map;
import autocity.core.Tile;
import autocity.exceptions.OutOfBoundsException;

/**
 * Warning: only supports Windows because Consolas
 */
public class SimpleUI extends Thread {
    private boolean isRunning = true;
    private long lastloop = System.nanoTime();
    private double delta = 0;

    private Game game;
    private UIFrame uiFrame;

    public SimpleUI(Game game) {
        this.game = game;
        this.uiFrame = new UIFrame();
    }

    public void run() {
        this.main();
    }

    private void main() {
        while (isRunning) {
            long now = System.nanoTime();
            long updateLength = now - lastloop;
            delta += ((double) updateLength / 1000000000);
            lastloop = now;

            if (delta >= 1) {
                this.redraw();
                delta = 0;
            }

            // Do stuff
        }
    }

    private void redraw() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.getHeaderText());
        sb.append(this.getMapText());

        this.uiFrame.setText(sb.toString());
    }

    private StringBuffer getHeaderText() {
        Map map = this.game.getMap();
        int height = map.getHeight();
        int width = map.getWidth();

        StringBuffer sb = new StringBuffer();

        sb.append(String.format("Rrerr~ Map Format: %dx%d, Number of settlements: %d", width, height, map.getSettlements().size()));

        sb.append('\n');
        sb.append('\n');

        return sb;
    }

    private StringBuffer getMapText() {
        Map map = this.game.getMap();
        int height = map.getHeight();
        int width = map.getWidth();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                try {
                    Tile tile = map.getTile(i, j);

                    sb.append(tile.getCharacter());
                } catch (OutOfBoundsException e) {
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
