package com.fuzzy.autocity.world.buildings.prefabs;

public class Military extends Building {
    public Military() {

    }

    public Military(Military m) {
        this.name = m.getName();
        this.width = m.getWidth();
        this.height = m.getHeight();
        this.character = m.getCharacter();
        this.maxConstructionTime = m.getMaxConstructionTime();
    }
}
