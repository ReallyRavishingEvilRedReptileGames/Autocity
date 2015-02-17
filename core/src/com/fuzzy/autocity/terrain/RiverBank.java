package com.fuzzy.autocity.terrain;

import com.fuzzy.autocity.Terrain;

public class RiverBank extends Sand {

    public RiverBank() {
        this.name = "River Bank";
        this.character = ',';
    }

    @Override
    public String getRandomTerrainObject() {
        return null;
    }
}
