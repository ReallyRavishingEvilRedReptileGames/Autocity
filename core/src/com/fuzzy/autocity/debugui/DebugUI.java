package com.fuzzy.autocity.debugui;

import com.fuzzy.autocity.Devmode;
import com.fuzzy.autocity.Game;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.World;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;

/**
 * Warning: only supports Windows because Consolas
 */
public class DebugUI extends Thread {
    private boolean isRunning = true;
    private long lastloop = System.nanoTime();
    private double delta = 0;
    private int targetfps = 5;
    private double targettime = 2 / (double) targetfps;

    private Game game;
    private UIFrame uiFrame;
    private Cursor cursor;
    private Devmode dev;

    public DebugUI(Game game) {
        this.game = game;
        this.cursor = new Cursor(this.game.getWorld());
        this.dev = new Devmode(game, cursor);
        this.uiFrame = new UIFrame(this, cursor, dev);

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
            } else {
                Thread.yield();
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }
            }
        }
    }

    private void redraw() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getMapText());

        this.uiFrame.setStatusText(this.getStatusText());
        this.uiFrame.setText(sb.toString());
    }

    private String getStatusText() {
        World world = this.game.getWorld();
        int width = world.getWidth();
        int height = world.getHeight();
        int x = cursor.getX();
        int y = cursor.getY();

        return String.format("Rrerr~ Map Format: %dx%d, Number of settlements: %d, X: %d Y: %d", width, height, world.getSettlements().size(), x, y);
    }

    private StringBuffer getMapText() {
        World world = this.game.getWorld();
        int width = world.getWidth();
        int height = world.getHeight();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cursor.getX() == j && cursor.getY() == i) {
                    sb.append(cursor.getCharacter());
                } else {
                    try {
                        Tile tile = world.getTile(j, i);

                        sb.append(tile.getCharacter());
                    } catch (TileOutOfBoundsException e) {
                        //not going to happen but we'll specify a case for this anyway.
                        sb.append('X');
                    }
                }

                sb.append(' ');
            }
            sb.append('\n');
        }

        return sb;
    }
}
