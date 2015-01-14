package autocity.core;

import autocity.core.tiles.WorldObject;
import autocity.core.tiles.buildings.prefabs.Building;
import autocity.exceptions.*;

public class ConstructionManager {
    private Map map;
    private Building building;
    private Settlement settlement;

    public ConstructionManager(Map map, Building building) {
        this.map = map;
        this.building = building;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void construct() throws PlacementAttemptsExceededException {
        PlacementValidator validator = new PlacementValidator(this.map);

        try {
            validator.validateBuilding(this.building, 1, 1);
        } catch (WorldObjectConflictException e) {
            System.out.println("Could not place building - conflicts with " + e.getWorldObject());
        } catch (TerrainConflictException e) {
            System.out.println("Could not place building - conflicts with terrain " + e.getTerrainType());
        } catch (OutOfBoundsException e) {
            System.out.println("Could not place building - out of bounds");
        }
    }
}
