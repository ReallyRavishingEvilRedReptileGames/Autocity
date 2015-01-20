package autocity.core;

import autocity.core.world.WorldObject;

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

    public WorldObject getOccupyingObject() {
        return occupyingObject;
    }

    public void setOccupyingObject(WorldObject occupyingObject) {
        if (this.occupyingObject != null) {
            this.occupyingObject.destroy();
        }

        this.occupyingObject = occupyingObject;
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
            switch (tmp[3].toLowerCase()) {
                case "getterrain":
                    System.out.println(getTerrain());
                    return;
                case "getoccupyingobject":
                    System.out.println(getOccupyingObject());
                    return;
                case "getheight":
                    System.out.println(getHeight());
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
