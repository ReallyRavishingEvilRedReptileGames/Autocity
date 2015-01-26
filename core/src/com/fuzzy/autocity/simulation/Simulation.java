package com.fuzzy.autocity.simulation;

import com.fuzzy.autocity.Game;
import com.fuzzy.autocity.Settlement;
import com.fuzzy.autocity.world.buildings.prefabs.Constructable;
import com.fuzzy.autocity.world.buildings.prefabs.Constructable;

import java.util.HashSet;
import java.util.Iterator;

public class Simulation {
    private Game game;

    public Simulation(Game game) {
        this.game = game;
    }

    public void onTick() {
        HashSet<Settlement> settlements = this.game.getWorld().getSettlements();
        HashSet<Constructable> constructions = this.game.getWorld().getConstructions();
        HashSet<Constructable> deconstructions = this.game.getWorld().getDeconstructions();

        for (Settlement settlement : settlements) {
            settlement.getPopulation().update();
        }
        for (Iterator<Constructable> i = constructions.iterator(); i.hasNext();) {
            Constructable c = i.next();
            if (c.isConstructed()) {
                i.remove();
            }
            c.Construct();
        }
        for (Iterator<Constructable> i = deconstructions.iterator(); i.hasNext();) {
            Constructable c = i.next();
            if (c == null || !c.isConstructed()) {
                i.remove();
            }
            c.deConstruct();
        }
    }


}
