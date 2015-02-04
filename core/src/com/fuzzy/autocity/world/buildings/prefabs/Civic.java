package com.fuzzy.autocity.world.buildings.prefabs;

public class Civic extends Building {
    public Civic() {
//        this.name = "Civic";
    }

    public Civic(Civic c) {
        this.name = c.getName();
        this.width = c.getWidth();
        this.height = c.getHeight();
        this.character = c.getCharacter();
    }
}
