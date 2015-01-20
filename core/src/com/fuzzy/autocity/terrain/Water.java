package com.fuzzy.autocity.terrain;

import com.fuzzy.autocity.Terrain;
import com.fuzzy.autocity.world.WorldObject;

public class Water extends Terrain {
    public Water() {
        this.name = "Water";
        this.character = '~';
        this.randomEntitySpawnRate = -1;
    }

    public WorldObject getRandomTerrainObject() {
        return null;
    }
}
