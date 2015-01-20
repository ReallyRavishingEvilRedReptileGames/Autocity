package com.fuzzy.autocity.terrain;

import com.fuzzy.autocity.Terrain;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.resources.PineTree;

public class Grass extends Terrain {
    public Grass() {
        this.name = "Grass";
        this.character = '.';
        this.randomEntitySpawnRate = 0.25;
    }

    public WorldObject getRandomTerrainObject() {
        return new PineTree();
    }
}
