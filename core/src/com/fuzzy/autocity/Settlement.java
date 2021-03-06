package com.fuzzy.autocity;

import com.fuzzy.autocity.civilians.prefabs.Villager;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.simulation.Population;
import com.fuzzy.autocity.world.buildings.Building;
import com.fuzzy.autocity.world.buildings.EArchetype;

import java.util.HashSet;

public class Settlement extends PlayerOwnable {
    private HashSet<Building> buildings;
    private HashSet<Villager> citizens;
    private World world;
    private int originX;
    private int originY;
    private Population population;

    public Settlement(World world, int originX, int originY) {
        this.initialize(world);
        this.originX = originX;
        this.originY = originY;
    }

    public Settlement(World world) {
        this.initialize(world);
    }

    public Settlement(World world, Player player) {
        this.initialize(world, player);
    }

    public double getBirthRate() {
        return 0.1;
    }

    private void initialize(World world) {
        this.world = world;
        this.buildings = new HashSet<>();
        this.citizens = new HashSet<>();
        this.population = new Population(this);
    }

    private void initialize(World world, Player player) {
        this.world = world;
        this.player = player;
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

    public HashSet<Building> getBuildings() {
        return this.buildings;
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
        return this.world.getTileSafe(this.originX, this.originY);
    }

    public int getPopulationCapacity() {
        int capacity = 0;

        for (Building building : this.buildings) {
            if (building.hasArchetype(EArchetype.Residential)) {
                capacity += building.getBaseCapacity();
            }
        }

        return capacity;
    }
}