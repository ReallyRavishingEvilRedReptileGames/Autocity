package com.fuzzy.autocity;

import com.fuzzy.autocity.world.WorldObject;

public abstract class Terrain {
    protected char character;
    protected String name;
    protected double randomEntitySpawnRate = 0.5;

    public char getCharacter() {
        return character;
    }

    public double getRandomEntitySpawnRate() {
        return randomEntitySpawnRate;
    }

    public abstract WorldObject getRandomTerrainObject();

    public String toString() {
        return this.name;
    }
}
