package autocity.core.world;

import autocity.core.Character;
import autocity.core.Tile;
import autocity.core.World;
import autocity.core.exceptions.TileOutOfBoundsException;

import java.util.HashSet;

public abstract class WorldObject implements Comparable<WorldObject> {
    protected int width = 1;
    protected int height = 1;
    protected String customName;
    protected String name = "Unknown World Object";
    protected char character = '#';

    protected HashSet<Character> visitors = new HashSet<>();
    protected HashSet<Tile> tiles = new HashSet<>();

    public WorldObject() {
    }

    public char getCharacter() {
        return this.character;
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

    public void placeAt(World world, int x, int y) {
        try {
            world.getTile(x, y).setOccupyingObject(this);
        } catch (TileOutOfBoundsException e) {

        }
    }
}
