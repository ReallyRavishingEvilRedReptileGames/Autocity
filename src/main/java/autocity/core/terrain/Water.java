package autocity.core.terrain;

import autocity.core.Terrain;
import autocity.core.world.WorldObject;

public class Water extends Terrain {
    public Water() {
        this.name = "Water";
        this.character = '~';
        this.randomEntitySpawnRate = -1;
    }

    public WorldObject getRandomTerrainObject() {
        return null;
    }
}
