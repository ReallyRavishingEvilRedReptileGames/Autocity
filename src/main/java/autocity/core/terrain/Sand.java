package autocity.core.terrain;

import autocity.core.Terrain;
import autocity.core.world.WorldObject;
import autocity.core.world.resources.PalmTree;

public class Sand extends Terrain {
    public Sand() {
        this.name = "Sand";
        this.character = ',';
        this.randomEntitySpawnRate = 0.05;
    }

    public WorldObject getRandomTerrainObject() {
        return new PalmTree();
    }
}
