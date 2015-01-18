package autocity.core;

import autocity.core.exceptions.BuildingConflictException;
import autocity.core.exceptions.TerrainConflictException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.exceptions.WorldObjectConflictException;
import autocity.core.terrain.Water;
import autocity.core.world.WorldObject;
import autocity.core.world.buildings.prefabs.Building;

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
                    Tile tile = world.getTile(i, j);

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

    public void validateWorldObject(WorldObject worldObject, int x, int y) throws WorldObjectConflictException, TileOutOfBoundsException {
        int width = worldObject.getWidth();
        int height = worldObject.getHeight();

        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if (this.world.getTile(i, j).getOccupyingObject() != null) {
                    throw new WorldObjectConflictException(this.world.getTile(i, j).getOccupyingObject());
                }
            }
        }
    }

    public void validateBuilding(Building building, int x, int y) throws WorldObjectConflictException, TileOutOfBoundsException, TerrainConflictException {
        this.validateWorldObject(building, x, y);
    }
}
