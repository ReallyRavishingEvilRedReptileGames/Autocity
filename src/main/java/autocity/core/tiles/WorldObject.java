package autocity.core.tiles;

import autocity.core.Person;
import autocity.core.Tile;

import java.util.HashSet;

public abstract class WorldObject implements Comparable<WorldObject> {
    protected int width = 1;
    protected int height = 1;
    protected String customName;

    protected HashSet<Person> visitors;

    public WorldObject() {
        this.visitors = new HashSet<>();
    }

    public HashSet<Person> getVisitors() {
        return visitors;
    }

    public void addVisitor(Person person) {
        this.visitors.add(person);
    }

    public void removeVisitor(Person person) {
        this.visitors.remove(person);
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
