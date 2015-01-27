package com.fuzzy.autocity.world.buildings;

import com.fuzzy.autocity.world.buildings.prefabs.Civic;

public class TownHall extends Civic {
    public TownHall() {
        this.name = "Town Hall";
        this.setWidth(3);
        this.setHeight(2);
        this.character = 'H';
    }

    public TownHall(Boolean b) {
        this.name = "Town Hall";
        this.setWidth(3);
        this.setHeight(2);
        this.character = 'H';
        this.constructed = b;
    }

}
