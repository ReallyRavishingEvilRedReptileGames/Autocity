package autocity.core;

public class Game extends Thread {
    private Map map;
    private boolean isRunning = true;
    private int tickrate = 1000;

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
            try {
                Thread.sleep(tickrate);
            } catch (InterruptedException e) {
                // Not important at all
            }

            System.out.println("Main game loop.");

            // Do stuff
        }

        this.onClose();
    }

    /**
     * Things to do on game exit
     */
    private void onClose()
    {

    }
}
