package autocity.core.exceptions;

import autocity.core.enumeration.ETerrainType;
import autocity.core.tiles.WorldObject;

public class TerrainConflictException extends Exception {
    private WorldObject worldObject;
    private ETerrainType terrainType;

    public TerrainConflictException(WorldObject worldObject, ETerrainType terrainType) {
        this.worldObject = worldObject;
        this.terrainType = terrainType;
    }

    public WorldObject getWorldObject() {
        return this.worldObject;
    }

    public ETerrainType getTerrainType() {
        return this.terrainType;
    }
}
