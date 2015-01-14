package autocity.core;

import autocity.core.tiles.WorldObject;
import autocity.core.tiles.buildings.prefabs.Building;
import autocity.exceptions.OutOfBoundsException;
import autocity.exceptions.WorldObjectConflictException;
import autocity.exceptions.TerrainConflictException;

public class PlacementValidator {
    private Map map;
    private int requiredSettlementFreeRadius = 10;

    public PlacementValidator(Map map) {
        this.map = map;
    }

    public void validateSettlement(Settlement settlement, int x, int y) throws WorldObjectConflictException {
        for (int i = x - requiredSettlementFreeRadius; i < x + requiredSettlementFreeRadius; i++) {
            if (i < 0 || i > this.map.getWidth()) {
                // Past the edge of the world
                continue;
            }

            for (int j = y - requiredSettlementFreeRadius; j < y + requiredSettlementFreeRadius; j++) {
                if (j < 0 || j > this.map.getHeight()) {
                    // Past the edge of the world
                    continue;
                }

                try {
                    Tile tile = map.getTile(i, j);

                    if (tile.getOccupyingObject() != null) {
                        throw new WorldObjectConflictException(tile.getOccupyingObject());
                    }
                } catch (OutOfBoundsException expected) {
                    // Not a problem
                }
            }
        }
    }

    public void validateWorldObject(WorldObject worldObject, int x, int y) throws WorldObjectConflictException, OutOfBoundsException {
        int width = worldObject.getWidth();
        int height = worldObject.getHeight();

        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if (this.map.getTile(i, j).getOccupyingObject() != null) {

                }
            }
        }
    }

    public void validateBuilding(Building building, int x, int y) throws WorldObjectConflictException, OutOfBoundsException, TerrainConflictException {
        this.validateWorldObject(building, x, y);
    }
}
