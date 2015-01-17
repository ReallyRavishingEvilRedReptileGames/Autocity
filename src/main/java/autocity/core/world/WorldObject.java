package autocity.core.world;

import autocity.core.Character;
import autocity.core.Tile;

import java.util.HashSet;

public abstract class WorldObject implements Comparable<WorldObject> {
    protected int width = 1;
    protected int height = 1;
    protected String customName;
    protected String name;

    protected HashSet<Character> visitors = new HashSet<>();
    protected HashSet<Tile> tiles = new HashSet<>();

    public WorldObject() {
        this.name = "Unknown World Object";
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
        return this.name;
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

    public void destroy() {
        for (Tile tile : tiles) {
            tile.setOccupyingObject(null);
        }

        this.onDestroy();
    }

    public void onDestroy() {

    }
}
