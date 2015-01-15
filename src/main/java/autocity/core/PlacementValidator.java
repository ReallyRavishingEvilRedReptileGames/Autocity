package autocity.core;

import autocity.core.exceptions.TerrainConflictException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.exceptions.WorldObjectConflictException;
import autocity.core.tiles.WorldObject;
import autocity.core.tiles.buildings.prefabs.Building;

public class PlacementValidator {
    private World world;
    private int requiredSettlementFreeRadius = 10;

    public PlacementValidator(World world) {
        this.world = world;
    }

    public void validateSettlement(Settlement settlement, int x, int y) throws WorldObjectConflictException {
        for (int i = x - requiredSettlementFreeRadius; i < x + requiredSettlementFreeRadius; i++) {
            for (int j = y - requiredSettlementFreeRadius; j < y + requiredSettlementFreeRadius; j++) {
                try {
                    Tile tile = world.getTile(i, j);

                    if (tile.getOccupyingObject() != null) {
                        throw new WorldObjectConflictException(tile.getOccupyingObject());
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
