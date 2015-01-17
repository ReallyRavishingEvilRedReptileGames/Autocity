package autocity.core;

import autocity.core.exceptions.PlacementAttemptsExceededException;
import autocity.core.exceptions.TerrainConflictException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.exceptions.WorldObjectConflictException;
import autocity.core.world.buildings.prefabs.Building;

public class ConstructionManager {
    private World world;
    private Building building;
    private int desiredX;
    private int desiredY;

    public ConstructionManager(World world, Building building) {
        this.world = world;
        this.building = building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public int getDesiredX() {
        return desiredX;
    }

    public void setDesiredX(int desiredX) {
        this.desiredX = desiredX;
    }

    public int getDesiredY() {
        return desiredY;
    }

    public void setDesiredY(int desiredY) {
        this.desiredY = desiredY;
    }

    public void construct() throws PlacementAttemptsExceededException {
        PlacementValidator validator = new PlacementValidator(this.world);

        try {
            validator.validateBuilding(this.building, this.desiredX, this.desiredY);
            this.world.getTile(this.desiredX, this.desiredY).setOccupyingObject(this.building);
        } catch (WorldObjectConflictException e) {
            System.out.println("Could not place building - conflicts with " + e.getWorldObject());
        } catch (TerrainConflictException e) {
            System.out.println("Could not place building - conflicts with terrain " + e.getTerrainType());
        } catch (TileOutOfBoundsException e) {
            System.out.println("Could not place building - out of bounds");
        }
    }
}
