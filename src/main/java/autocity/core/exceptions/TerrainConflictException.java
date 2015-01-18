package autocity.core.exceptions;

import autocity.core.Terrain;

public class TerrainConflictException extends Exception {
    private Terrain terrain;

    public TerrainConflictException(Terrain terrain) {
        this.terrain = terrain;
    }

    public Terrain getTerrain() {
        return this.terrain;
    }
}
