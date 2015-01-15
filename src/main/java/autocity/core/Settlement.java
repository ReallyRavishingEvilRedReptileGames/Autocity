package autocity.core;

import autocity.core.civilians.prefabs.Villager;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.simulation.Population;
import autocity.core.world.buildings.prefabs.Building;

import java.util.HashSet;

public class Settlement {
    private HashSet<Building> buildings;
    private HashSet<Villager> citizens;
    private World world;
    private int originX;
    private int originY;
    private Player owner;
    private Population population;

    public double getBirthRate() {
        return 0.1;
    }

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

    public void addCitizen(Villager villager) {
        this.citizens.add(villager);
    }

    public void removeCitizen(Villager villager) {
        this.citizens.remove(villager);
    }

    public HashSet<Villager> getCitizens() {
        return this.citizens;
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