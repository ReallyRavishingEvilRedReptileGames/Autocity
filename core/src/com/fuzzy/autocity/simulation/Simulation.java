package com.fuzzy.autocity.simulation;

import com.fuzzy.autocity.Game;
import com.fuzzy.autocity.Settlement;
import com.fuzzy.autocity.world.Construction;
import com.fuzzy.autocity.world.buildings.GenericConstruction;

import java.util.HashSet;
import java.util.Iterator;

public class Simulation {
    private Game game;

    public Simulation(Game game) {
        this.game = game;
    }

    public void onTick() {
        HashSet<Settlement> settlements = this.game.getWorld().getSettlements();
        HashSet<GenericConstruction> constructions = this.game.getWorld().getConstructions();
        HashSet<GenericConstruction> deconstructions = this.game.getWorld().getDeconstructions();

        for (Settlement settlement : settlements) {
            settlement.getPopulation().update();
        }
        for (Iterator<GenericConstruction> i = constructions.iterator(); i.hasNext();) {
            GenericConstruction c = i.next();
            if (c.isConstructed() || c == null) {
                this.game.getWorld().placeConstruction(c.getConstruction(), c.getConstruction().getOriginTile()); // Null tile
                c.destroy();
                i.remove();
            } else {
                c.Construct();
            }
        }
        for (Iterator<GenericConstruction> i = deconstructions.iterator(); i.hasNext();) {
            GenericConstruction c = i.next();
            if (c == null || !c.isConstructed()) {
                i.remove();
            } else {
                c.deConstruct();
            }
        }
    }


}
