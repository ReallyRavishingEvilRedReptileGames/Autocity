package com.fuzzy.autocity.terrain;

import com.fuzzy.autocity.Terrain;

public class Mountain extends Terrain {

    public Mountain() {
        this.name = "Mountain";
        this.character = '/';
        this.randomEntitySpawnRate = 0.1;
    }

    @Override
    public String getRandomTerrainObject() {
        return null;
    }
}
