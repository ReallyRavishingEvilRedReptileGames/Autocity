package autocity.core.tiles;

import autocity.core.Character;
import autocity.core.Tile;

import java.util.HashSet;

public abstract class WorldObject implements Comparable<WorldObject> {
    protected int width = 1;
    protected int height = 1;
    protected String customName;

    protected HashSet<Character> visitors;
    protected HashSet<Tile> tiles;

    public WorldObject() {
        this.visitors = new HashSet<>();
    }

    public HashSet<Character> getVisitors() {
        return visitors;
    }

    public void addVisitor(Character character) {
        this.visitors.add(character);
    }

    public void removeVisitor(Character character) {
        this.visitors.remove(character);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int compareTo(WorldObject worldObject) {
        return 0;
    }

    public String getName() {
        return "World Object";
    }

    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String toString() {
        return this.customName == null ? this.getName() : this.customName;
    }
}
