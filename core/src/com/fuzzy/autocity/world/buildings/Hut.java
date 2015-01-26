package com.fuzzy.autocity.world.buildings;

import com.fuzzy.autocity.world.buildings.prefabs.Constructable;
import com.fuzzy.autocity.world.buildings.prefabs.Residential;

public class Hut extends Residential {
    public Hut() {
        this.name = "Hut";
        this.width = 2;
        this.height = 2;
        this.character = '^';
        this.maxConstructionTime = 1;
    }

    public Hut(Boolean b) {
        this.name = "Hut";
        this.width = 2;
        this.height = 2;
        this.character = '^';
        this.constructed = b;
    }
}
