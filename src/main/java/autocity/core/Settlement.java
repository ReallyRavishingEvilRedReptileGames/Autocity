package autocity.core;

import autocity.core.generators.RoadBuilder;
import autocity.core.tiles.buildings.Hut;
import autocity.core.tiles.buildings.TownHall;
import autocity.core.tiles.buildings.prefabs.Building;
import autocity.exceptions.TileOutOfBoundsException;
import autocity.exceptions.WorldObjectConflictException;
import autocity.exceptions.PlacementAttemptsExceededException;

import java.util.HashSet;
import java.util.Random;

public class Settlement {
    private HashSet<Building> buildings;
    private HashSet<Person> persons;
    private Map map;
    private int originX;
    private int originY;
    private Player owner;

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Settlement(Map map, int originX, int originY) {
        this.buildings = new HashSet<>();
        this.persons = new HashSet<>();
        this.map = map;
        this.originX = originX;
        this.originY = originY;
    }

    public Settlement(Map map) {
        this.buildings = new HashSet<>();
        this.persons = new HashSet<>();
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
    public void found() {
        this.addRoads();
        this.addBuilding(new Hut());
        this.addBuilding(new TownHall());
        this.addBuilding(new Hut());
    }

    private void addRoads() {
        RoadBuilder builder = new RoadBuilder(this);
        builder.generateStartingRoads();
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

    public Tile getOriginTile() throws TileOutOfBoundsException {
        return this.map.getTile(this.originX, this.originY);
    }
}