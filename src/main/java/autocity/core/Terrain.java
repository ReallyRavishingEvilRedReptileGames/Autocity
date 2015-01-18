package autocity.core;

import autocity.core.world.WorldObject;

/**
 * Created by Fiskie on 18/01/2015.
 */
public abstract class Terrain {
    protected char character;
    protected String name;
    protected WorldObject[] randomEntities;
    protected int randomEntitySpawnRate = 4;

    public char getCharacter() {
        return character;
    }

    public WorldObject[] getRandomEntities() {
        return randomEntities;
    }

    public int getRandomEntitySpawnRate() {
        return randomEntitySpawnRate;
    }

    public String toString() {
        return this.name;
    }
}
