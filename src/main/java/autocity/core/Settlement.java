package autocity.core;

import autocity.core.exceptions.PlacementAttemptsExceededException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.exceptions.WorldObjectConflictException;
import autocity.core.factories.SettlementFactory;
import autocity.core.simulation.Population;
import autocity.core.tiles.buildings.prefabs.Building;

import java.util.HashSet;
import java.util.Random;

public class Settlement {
    private HashSet<Building> buildings;
    private HashSet<Character> citizens;
    private World world;
    private int originX;
    private int originY;
    private Player owner;
    private Population population;

    public Settlement(World world, int originX, int originY) {
        this.initialize(world);
        this.originX = originX;
        this.originY = originY;
    }

    public Settlement(World world) {
        this.initialize(world);
    }

    private void initialize(World world) {
        this.world = world;
        this.buildings = new HashSet<>();
        this.citizens = new HashSet<>();
        this.population = new Population(this);
    }

    public Population getPopulation() {
        return this.population;
    }

    public void addCitizen(Character character) {
        this.citizens.add(character);
    }

    public void removeCitizen(Character character) {
        this.citizens.remove(character);
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    public void removeBuilding(Building building) {
        this.buildings.remove(building);
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Find a location for this settlement and place it there.
     */
    public void autoPlace() throws PlacementAttemptsExceededException {
        int width = this.world.getWidth();
        int height = this.world.getHeight();
        int placementAttempts = 5;
        boolean placed = false;

        Random rand = new Random();

        PlacementValidator validator = new PlacementValidator(this.world);

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
        SettlementFactory settlementFactory = new SettlementFactory(this);
        settlementFactory.generate();
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

    public World getWorld() {
        return world;
    }

    public Tile getOriginTile() throws TileOutOfBoundsException {
        return this.world.getTile(this.originX, this.originY);
    }
}