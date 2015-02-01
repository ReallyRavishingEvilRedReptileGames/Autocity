package com.fuzzy.autocity.world;

import com.fuzzy.autocity.Character;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.World;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;

import java.util.HashSet;

public abstract class WorldObject implements Comparable<WorldObject> {
    protected int width = 1;
    protected int height = 1;
    protected String customName;
    protected String name = "Unknown World Object";
    protected char character = '!';

    protected HashSet<Character> visitors = new HashSet<>();
    protected HashSet<Tile> tiles = new HashSet<>();

    public HashSet<Character> getVisitors() {
        return this.visitors;
    }

    public void addVisitor(Character character) {
        this.visitors.add(character);
    }

    public void removeVisitor(Character character) {
        this.visitors.remove(character);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    protected void setWidth(int x) {
        this.width = x;
    }

    protected void setHeight(int y) {
        this.height = y;
    }

    public int compareTo(WorldObject worldObject) {
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public char getCharacter() {
        return this.character;
    }

    public String toString() {
        return this.customName == null ? this.getName() : this.customName;
    }

    public void addTile(Tile t) {
        tiles.add(t);
    }

    public HashSet<Tile> getTiles() {
        return tiles;
    }

    public void destroy() {
        for (Tile tile : tiles) {
            tile.setOccupyingObject(null);
        }

        this.onDestroy();
    }

    public void onDestroy() {

    }

    public void placeAt(World world, int x, int y) {
        try {
            world.getTileSafe(x, y).setOccupyingObject(this);
        } catch (TileOutOfBoundsException ignored) {

        }
    }
}
