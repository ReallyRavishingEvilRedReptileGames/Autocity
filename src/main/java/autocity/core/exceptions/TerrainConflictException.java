package autocity.core.exceptions;

import autocity.core.enumeration.ETerrainType;
import autocity.core.world.WorldObject;

public class TerrainConflictException extends Exception {
    private ETerrainType terrainType;

    public TerrainConflictException(ETerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public ETerrainType getTerrainType() {
        return this.terrainType;
    }
}
