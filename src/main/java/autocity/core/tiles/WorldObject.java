package autocity.core.tiles;

import autocity.core.Person;
import autocity.core.Tile;

import java.util.Comparator;
import java.util.HashSet;

public abstract class WorldObject implements Comparable<WorldObject> {
    protected int width = 1;
    protected int height = 1;

    protected HashSet<Person> visitors;

    public HashSet<Person> getVisitors() {
        return visitors;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    protected HashSet<Tile> tiles;

    public int compareTo(WorldObject worldObject) {
        return 0;
    }
}
