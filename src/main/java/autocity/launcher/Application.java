package autocity.launcher;

import autocity.core.Game;
import autocity.simpleui.SimpleUI;

class Application {
    public static void main(String[] args) {
        Game game = new Game();
        SimpleUI ui = new SimpleUI(game);

        // Start main game
        game.start();
        ui.start();
    }
}