package com.fuzzy.autocity.world.buildings.prefabs;

public class Special extends Building {

    public Special() {

    }

    public Special(Special r) {
        this.name = r.getName();
        this.width = r.getWidth();
        this.height = r.getHeight();
        this.character = r.getCharacter();
        this.maxConstructionTime = r.getMaxConstructionTime();
    }
}
