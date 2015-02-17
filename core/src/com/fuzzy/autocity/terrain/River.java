package com.fuzzy.autocity.terrain;

import com.fuzzy.autocity.Terrain;

public class River extends Terrain {

    public River() {
        this.name = "River";
        this.character = '@';
        this.randomEntitySpawnRate = -1;
    }


    @Override
    public String getRandomTerrainObject() {
        return null;
    }
}
