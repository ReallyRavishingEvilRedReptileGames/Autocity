package com.fuzzy.autocity.terrain;

import com.fuzzy.autocity.Terrain;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.resources.PalmTree;

public class Sand extends Terrain {
    public Sand() {
        this.name = "Sand";
        this.character = ',';
        this.randomEntitySpawnRate = 0.05;
    }

    public WorldObject getRandomTerrainObject() {
        return new PalmTree();
    }
}
