package autocity.core.tiles;

import autocity.core.Tile;
import autocity.enums.EBuildingStyle;
import autocity.exceptions.CannotPlaceException;

import java.util.ArrayList;

public abstract class WorldObject {
    protected int width = 1;
    protected int height = 1;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    protected ArrayList<Tile> tiles;
}
