package com.fuzzy.autocity.terrain;

import com.fuzzy.autocity.Terrain;

public class RiverBank extends Sand {

    public RiverBank() {
        this.name = "River Bank";
        this.character = ',';
        this.randomEntitySpawnRate = -1;
    }

    @Override
    public String getRandomTerrainObject() {
        return null;
    }
}
