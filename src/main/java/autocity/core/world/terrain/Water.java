package autocity.core.world.terrain;

import autocity.core.Tile;

/**
 * Created by Whiplash on 1/17/2015.
 */
public class Water extends Tile {

    public Water(int x, int y) {
        super(x, y);
        character = '~';
    }
}
