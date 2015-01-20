package com.fuzzy.autocity.exceptions;

import com.fuzzy.autocity.Terrain;

public class TerrainConflictException extends Exception {
    private Terrain terrain;

    public TerrainConflictException(Terrain terrain) {
        this.terrain = terrain;
    }

    public Terrain getTerrain() {
        return this.terrain;
    }
}
