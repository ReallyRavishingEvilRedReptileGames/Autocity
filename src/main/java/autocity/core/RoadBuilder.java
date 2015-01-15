package autocity.core;

import autocity.core.tiles.paths.Road;
import autocity.exceptions.TileOutOfBoundsException;

public class RoadBuilder {
    private Settlement settlement;

    public RoadBuilder(Settlement settlement) {
        this.settlement = settlement;
    }

    public void generateStartingRoads() {
        Road road = new Road();

        try {
            this.settlement.getOriginTile().setOccupyingObject(road);
        } catch (TileOutOfBoundsException e) {
            // Not expected
        }
    }
}
