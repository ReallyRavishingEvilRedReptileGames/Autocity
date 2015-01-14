package autocity.core;

public class Game extends Thread {
    private Map map;
    private boolean isRunning = true;
    private long lastloop = System.nanoTime();
    private double delta = 0;

    public Map getMap() {
        return this.map;
    }

    public Game() {
        System.out.println("Generating map...");
        WorldBuilder builder = new WorldBuilder();
        this.map = builder.generate();
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
                System.out.println("Main game loop.");
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
}
