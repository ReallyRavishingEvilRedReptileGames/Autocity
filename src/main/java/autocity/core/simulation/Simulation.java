package autocity.core.simulation;

import autocity.core.Game;
import autocity.core.Settlement;

import java.util.HashSet;

public class Simulation {
    private Game game;

    public Simulation(Game game) {
        this.game = game;
    }

    public void onTick() {
        HashSet<Settlement> settlements = this.game.getWorld().getSettlements();

        for (Settlement settlement : settlements) {
            settlement.getPopulation().update();
        }
    }
}
