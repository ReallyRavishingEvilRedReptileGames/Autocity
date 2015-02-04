package com.fuzzy.autocity.world.buildings;

import com.fuzzy.autocity.world.buildings.prefabs.Residential;

public class Hut extends Residential {
    public Hut() {
        this.name = "Hut";
        this.setWidth(2);
        this.setHeight(2);
        this.character = '^';
        this.maxConstructionTime = 1;
    }

    public Hut(Hut h) {
        this.name = h.getName();
        this.setWidth(h.getWidth());
        this.setHeight(h.getHeight());
        this.character = h.getCharacter();
        this.maxConstructionTime = h.getMaxConstructionTime();
    }

}
