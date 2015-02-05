package com.fuzzy.autocity.world.buildings.prefabs;

public class Store extends Building {

    public Store() {

    }

    public Store(Store s) {
        this.name = s.getName();
        this.width = s.getWidth();
        this.height = s.getHeight();
        this.character = s.getCharacter();
        this.maxConstructionTime = s.getMaxConstructionTime();
    }
}