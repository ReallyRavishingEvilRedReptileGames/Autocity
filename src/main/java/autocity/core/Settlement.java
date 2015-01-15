package autocity.core;

import autocity.core.exceptions.PlacementAttemptsExceededException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.exceptions.WorldObjectConflictException;
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