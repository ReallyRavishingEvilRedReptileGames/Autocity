package autocity.core;

import autocity.core.world.WorldObject;

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
}
