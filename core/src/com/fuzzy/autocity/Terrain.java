package com.fuzzy.autocity;

import com.badlogic.gdx.graphics.Texture;
import com.fuzzy.autocity.world.WorldObject;

public abstract class Terrain {
    protected char character;
    protected String name;
    protected double randomEntitySpawnRate = 0.5;
    protected Texture texture;

    public char getCharacter() {
        return this.character;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public double getRandomEntitySpawnRate() {
        return this.randomEntitySpawnRate;
    }

    public abstract WorldObject getRandomTerrainObject();

    public String toString() {
        return this.name;
    }
}
