package autocity.core;

import autocity.core.exceptions.PlacementAttemptsExceededException;
import autocity.core.exceptions.TerrainConflictException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.exceptions.WorldObjectConflictException;
import autocity.core.world.buildings.prefabs.Building;

public class ConstructionManager {
    private World world;
    private Building building;
    private Settlement settlement;

    public ConstructionManager(World world, Building building) {
        this.world = world;
        this.building = building;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void construct() throws PlacementAttemptsExceededException {
        PlacementValidator validator = new PlacementValidator(this.world);

        try {
            validator.validateBuilding(this.building, this.settlement.getOriginX(), this.settlement.getOriginY());
            this.world.getTile(this.settlement.getOriginX(), this.settlement.getOriginY()).setOccupyingObject(this.building);
        } catch (WorldObjectConflictException e) {
            System.out.println("Could not place building - conflicts with " + e.getWorldObject());
        } catch (TerrainConflictException e) {
            System.out.println("Could not place building - conflicts with terrain " + e.getTerrainType());
        } catch (TileOutOfBoundsException e) {
            System.out.println("Could not place building - out of bounds");
        }
    }
}
