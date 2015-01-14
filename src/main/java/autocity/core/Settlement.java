package autocity.core;

import autocity.core.tiles.buildings.Shack;
import autocity.core.tiles.buildings.TownHall;
import autocity.core.tiles.buildings.prefabs.Building;
import autocity.exceptions.OutOfBoundsException;
import autocity.exceptions.WorldObjectConflictException;
import autocity.exceptions.PlacementAttemptsExceededException;

import java.util.ArrayList;
import java.util.Random;

public class Settlement {
    private ArrayList<Building> buildings;
    private ArrayList<Civilian> civilians;
    private Map map;
    private int originX;
    private int originY;

    public Settlement(Map map, int originX, int originY) {
        this.buildings = new ArrayList<>();
        this.civilians = new ArrayList<>();
        this.map = map;
        this.originX = originX;
        this.originY = originY;
    }

    public Settlement(Map map) {
        this.buildings = new ArrayList<>();
        this.civilians = new ArrayList<>();
        this.map = map;
    }

    /**
     * Find a location for this settlement and place it there.
     */
    public void autoPlace() throws PlacementAttemptsExceededException {
        int width = this.map.getWidth();
        int height = this.map.getHeight();
        int placementAttempts = 5;
        boolean placed = false;

        Random rand = new Random();

        PlacementValidator validator = new PlacementValidator(this.map);

        for (int i = 0; i < placementAttempts; i++) {
            int nextX = rand.nextInt(width);
            int nextY = rand.nextInt(height);

            try {
                validator.validateSettlement(this, nextX, nextY);
                this.originX = nextX;
                this.originY = nextY;
                placed = true;
                System.out.println("Placed settlement at (" + this.originX + "," + this.originY + ")");
                break;
            } catch (WorldObjectConflictException e) {
                System.out.println("Settlement conflicts with " + e.getWorldObject());
                // Settlement placement will conflict with a building
            }
        }

        if (!placed) {
            System.out.println("Unable to place settlement, cancelling.");
            throw new PlacementAttemptsExceededException();
        }
    }

    /**
     * Generates entities for a basic town.
     */
    public void generateStarter() {
        this.addRoads();
        this.addBuilding(new Shack());
        this.addBuilding(new TownHall());
        this.addBuilding(new Shack());
    }

    private void addRoads() {

    }

    private void addBuilding(Building building) {
        building.setSettlement(this);

        ConstructionManager manager = new ConstructionManager(this.map, building);

        manager.setSettlement(this);
        manager.setBuilding(building);

        try {
            manager.construct();
            this.buildings.add(building);
        } catch (PlacementAttemptsExceededException e) {

        }
    }

    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

    public Map getMap() {
        return map;
    }
}