package com.fuzzy.autocity;

import com.fuzzy.autocity.exceptions.BuildingConflictException;
import com.fuzzy.autocity.exceptions.TerrainConflictException;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.exceptions.WorldObjectConflictException;
import com.fuzzy.autocity.terrain.Water;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.buildings.Building;

public class PlacementValidator {
    private World world;
    private int requiredSettlementFreeRadius = 10;

    public PlacementValidator(World world) {
        this.world = world;
    }

    public void validateSettlement(Settlement settlement, int x, int y) throws BuildingConflictException, TerrainConflictException {
        for (int i = x - requiredSettlementFreeRadius; i < x + requiredSettlementFreeRadius; i++) {
            for (int j = y - requiredSettlementFreeRadius; j < y + requiredSettlementFreeRadius; j++) {
                try {
                    Tile tile = world.getTileSafe(i, j);

                    WorldObject worldObject = tile.getOccupyingObject();

                    if (worldObject != null && worldObject instanceof Building) {
                        throw new BuildingConflictException((Building) worldObject);
                    }

                    if (tile.getTerrain() instanceof Water) {
                        throw new TerrainConflictException(tile.getTerrain());
                    }
                } catch (TileOutOfBoundsException expected) {
                    // Not a problem
                }
            }
        }
    }

    public void validateWorldObject(WorldObject worldObject, int x, int y) throws WorldObjectConflictException, TileOutOfBoundsException, TerrainConflictException {
        int width = worldObject.getWidth();
        int height = worldObject.getHeight();

        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if (this.world.getTileSafe(i, j).getOccupyingObject() != null) {
                    throw new WorldObjectConflictException(this.world.getTileSafe(i, j).getOccupyingObject());
                } else if (this.world.getTileSafe(i, j).getTerrain() instanceof Water) {
                    throw new TerrainConflictException(this.world.getTileSafe(i, j).getTerrain());
                }
            }
        }
    }

    public void validateBuilding(Building building, int x, int y) throws WorldObjectConflictException, TileOutOfBoundsException, TerrainConflictException {
        this.validateWorldObject(building, x, y);
    }
}
