package com.fuzzy.autocity.world.buildings.prefabs;

public class Research extends Building {

    public Research() {

    }

    public Research(Research r) {
        this.name = r.getName();
        this.width = r.getWidth();
        this.height = r.getHeight();
        this.character = r.getCharacter();
        this.maxConstructionTime = r.getMaxConstructionTime();
    }
}
