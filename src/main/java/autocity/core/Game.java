package autocity.core;

import autocity.core.factories.WorldFactory;
import autocity.core.simulation.Simulation;

public class Game extends Thread {
    private World world;
    private boolean isRunning = true;
    private long lastloop = System.nanoTime();
    private double delta = 0;
    private Simulation simulation;

    public Game() {
        this.startGame();
    }

    public void restartGame() {
        this.simulation = null;
        this.world = null;
        this.startGame();
    }

    public void startGame() {
        System.out.println("Generating world...");
        WorldFactory builder = new WorldFactory();
        this.world = builder.generate(64);
        this.simulation = new Simulation(this);
    }

    public World getWorld() {
        return this.world;
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
            long updateLength = now - lastloop;
            delta += ((double) updateLength / 1000000000);
            lastloop = now;

            if (delta >= 1) {
                this.onTick();
                delta = 0;
            }

            // Do stuff
        }

        this.onClose();
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
