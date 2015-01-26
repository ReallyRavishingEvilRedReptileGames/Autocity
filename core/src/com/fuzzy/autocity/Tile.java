package com.fuzzy.autocity;

import com.fuzzy.autocity.simulation.Simulation;
import com.fuzzy.autocity.terrain.*;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.buildings.prefabs.Building;

public class Tile implements Invokable {
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

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    private void setTerrain(String s) {
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

    public WorldObject getOccupyingObject() {
        return occupyingObject;
    }

    public void setOccupyingObject(WorldObject occupyingObject) {
        if (this.occupyingObject != null) {
            this.occupyingObject.destroy();
        }

        this.occupyingObject = occupyingObject;
    }

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

    public int getHeight() {
        return height;
    }

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

    @Override
    public void Execute(String command) {
        String[] tmp = command.split(delimiter);
        try {
            switch (tmp[1].toLowerCase()) {
                case "help":
                    System.out.println("GetTerrain, SetTerrain, GetOccupyingObject, GetHeight, ToString, GetCharacter");
                    return;
                case "getterrain":
                    System.out.println(getTerrain());
                    return;
                case "setterrain":
                    setTerrain(tmp[2]);
                    System.out.println(getTerrain());
                    return;
                case "getoccupyingobject":
                    System.out.println(getOccupyingObject());
                    return;
                case "getheight":
                    System.out.println(getHeight());
                    return;
                case "setheight":
                    try {
                        setHeight(Integer.valueOf(tmp[2]));
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid command.");
                        System.out.println(" @" + getClass().getName());
                    }
                    return;
                case "tostring":
                    System.out.println(toString());
                    return;
                case "getcharacter":
                    System.out.println(getCharacter());
                    return;
                default:
                    System.out.println("Invalid command.");
                    System.out.println(" @" + getClass().getName());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid command.");
            System.out.println(" @" + getClass().getName());
        }
    }
}
