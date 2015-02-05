package com.fuzzy.autocity.world.buildings.prefabs;

public class Industrial extends Building {

    public Industrial() {

    }

    public Industrial(Industrial s) {
        this.name = s.getName();
        this.width = s.getWidth();
        this.height = s.getHeight();
        this.character = s.getCharacter();
        this.maxConstructionTime = s.getMaxConstructionTime();
    }
}