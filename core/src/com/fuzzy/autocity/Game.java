package com.fuzzy.autocity;

import com.fuzzy.autocity.debugui.Cursor;
import com.fuzzy.autocity.factories.WorldFactory;
import com.fuzzy.autocity.factories.WorldObjectFactory;
import com.fuzzy.autocity.simulation.Simulation;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.buildings.prefabs.Construction;

import java.util.ArrayList;

public class Game extends Thread implements Invokable {
    private World world;
    private boolean isRunning = true;
    private long lastLoop = System.nanoTime();
    private double delta = 0;
    private Simulation simulation;
    private Cursor cursor;
    private Devmode dev;
    private static ArrayList<Player> playerList = new ArrayList<>();

    public Game() {
        this.startGame();
    }

    public void restartGame() {
        this.simulation = null;
        this.world = null;
        this.cursor = null;
        this.dev = null;
        playerList.clear();
        this.startGame();
    }

    public void startGame() {
        playerList.add(new HumanPlayer());
        System.out.println("Generating world...");
        WorldFactory builder = new WorldFactory();
        this.world = builder.generate(150, 100);
        this.simulation = new Simulation(this);
        this.cursor = new Cursor(this);
        this.dev = new Devmode(this, this.cursor);
    }

    private void newMap(int x, int y) {
        System.out.println("Generating world...");
        WorldFactory builder = new WorldFactory();
        this.world = builder.generate(x, y);
        this.simulation = new Simulation(this);
        this.cursor = new Cursor(this);
        this.dev = new Devmode(this, this.cursor);
    }

    public World getWorld() {
        return this.world;
    }

    public Cursor getCursor() {
        return this.cursor;
    }

    public Devmode getDev() {
        return this.dev;
    }

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

    @Override
    public void Execute(String command) {
        String[] tmp = command.split(delimiter);
        switch (tmp[1]) {
            case "newmap":
                try {
                    newMap(Integer.valueOf(tmp[2]), Integer.valueOf(tmp[3]));
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid command.");
                    System.out.println(" @" + this.getClass().getName());
                }
                return;
            case "placeworldobject":
            case "place":
                WorldObject o = new WorldObjectFactory().createWorldObject(tmp[2]);
                Tile t = this.world.getTile(this.cursor.getX(), this.cursor.getY());
                if (o instanceof Construction) {
                    this.world.buildConstruction((Construction) o, t);
                } else {
                    this.world.placeWorldObject(o, t);
                }
                return;
            case "removeconstructable":
                WorldObject ro = this.world.getTile(this.cursor.getX(), this.cursor.getY()).getOccupyingObject();
                if (ro instanceof Construction) {
                    this.world.removeConstruction((Construction) ro);
                }
                return;
            default:
                System.out.println("Invalid command.");
                System.out.println(" @" + this.getClass().getName());
        }
    }
}
