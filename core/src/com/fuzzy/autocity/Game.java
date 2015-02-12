package com.fuzzy.autocity;

import com.fuzzy.autocity.debugui.Cursor;
import com.fuzzy.autocity.factories.WorldFactory;
import com.fuzzy.autocity.simulation.Simulation;

import java.util.ArrayList;

public class Game extends Thread {
    private World world;
    private boolean isRunning = true;
    private long lastLoop = System.nanoTime();
    private double delta = 0;
    private Simulation simulation;
    private Cursor cursor;
    private DevelopmentMode dev;
    private static ArrayList<Player> playerList = new ArrayList<>();

    public Game() {
        playerList.add(new HumanPlayer());
        this.startGame();
    }

    @Invokable
    public void restartGame() {
        this.simulation = null;
        this.world = null;
        this.cursor = null;
        this.dev = null;
        this.startGame();
    }

    public void restartGame(int x, int y) {
        this.simulation = null;
        this.world = null;
        this.cursor = null;
        this.dev = null;
        this.startGame(x, y);
    }

    public void startGame() {
        System.out.println("Generating world...");
        WorldFactory builder = new WorldFactory();
        this.world = builder.generate(150, 100);
        this.simulation = new Simulation(this);
        this.cursor = new Cursor(this);
        this.dev = new DevelopmentMode(this, this.cursor);
    }

    private void startGame(int x, int y) {
        System.out.println("Generating world...");
        WorldFactory builder = new WorldFactory();
        this.world = builder.generate(x, y);
        this.simulation = new Simulation(this);
        this.cursor = new Cursor(this);
        this.dev = new DevelopmentMode(this, this.cursor);
    }

    public World getWorld() {
        return this.world;
    }

    public Cursor getCursor() {
        return this.cursor;
    }

    public DevelopmentMode getDev() {
        return this.dev;
    }

    @Invokable
    public static Player getPlayer() {
        return playerList.get(0);
    }

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void run() {
        this.main();
    }

    /**
     * Main game loop
     */
    private void main() {
        while (isRunning) {

            long now = System.nanoTime();
            long updateLength = now - lastLoop;
            delta += ((double) updateLength / 1000000000);
            lastLoop = now;
            if (delta >= 0.05) { // Game logic updates 1/20th a second.
                this.onTick();
                delta = 0;
            } else {
                Thread.yield();
                try {
                    Thread.sleep(1);
                } catch (Exception ignored) {
                }
            }

            this.onClose();
        }
    }

    /**
     * Things to do on game exit
     */
    private void onClose() {

    }

    /**
     * Things to do on every game tick
     */
    private void onTick() {
        this.simulation.onTick();
    }

}
