package autocity.exceptions;

import autocity.core.tiles.WorldObject;
import autocity.core.tiles.buildings.prefabs.Building;
import autocity.enums.ETerrainType;

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
