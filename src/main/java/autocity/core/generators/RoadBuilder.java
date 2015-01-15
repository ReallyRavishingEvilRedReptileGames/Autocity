package autocity.core.generators;

import autocity.core.Settlement;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.tiles.paths.Road;

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
