package com.fuzzy.autocity.world.buildings;

import com.fuzzy.autocity.world.buildings.prefabs.Research;

public class Alchemist extends Research {
    public Alchemist() {
        this.name = "Alchemist";
    }

    public Alchemist(Boolean b) {
        this.name = "Alchemist";
        this.constructed = b;
    }

}
