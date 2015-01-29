package com.fuzzy.autocity.world.buildings;

import com.fuzzy.autocity.world.buildings.prefabs.Store;

public class Smithy extends Store {
    public Smithy() {
        this.name = "Smithy";
    }

    public Smithy(Boolean b) {
        this.name = "Smithy";
        this.constructed = b;
    }
}
