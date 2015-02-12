package com.fuzzy.autocity;

import com.fuzzy.autocity.terrain.*;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.buildings.Building;

public class Tile {
    protected char character;
    private WorldObject occupyingObject;
    private Terrain terrain;
    private int x;
    private int y;
    private int height;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Invokable
    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    @Invokable
    public void setTerrain(String s) { // Eventually this will use a dictionary lookup to set the terrain
        switch (s.toLowerCase()) {
            case "grass":
                this.terrain = new Grass();
                return;
            case "sand":
                this.terrain = new Sand();
                return;
            case "water":
                this.terrain = new Water();
                return;
            default:
                System.out.println("Nope.");
        }
    }

    @Invokable
    public WorldObject getOccupyingObject() {
        return occupyingObject;
    }

    public void setOccupyingObject(WorldObject occupyingObject) {
        this.occupyingObject = occupyingObject;
    }

    @Invokable
    public void placeBuilding(Building b) {
        if (this.occupyingObject != null) {
            this.occupyingObject.destroy();
        }
        this.occupyingObject = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Invokable
    public int getHeight() {
        return height;
    }

    @Invokable
    public void setHeight(int height) {
        this.height = height;
    }

    public String toString() {
        return "Tile at (" + this.x + "," + this.y + "), " + (this.occupyingObject == null ? "no occupying building" : "occupied by " + this.occupyingObject);
    }

    public char getCharacter() {
        if (this.occupyingObject == null) {
            return this.terrain.getCharacter();
        } else {
            return this.occupyingObject.getCharacter();
        }
    }

}
