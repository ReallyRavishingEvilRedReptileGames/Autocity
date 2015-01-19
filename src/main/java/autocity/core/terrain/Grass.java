package autocity.core.terrain;

import autocity.core.Terrain;
import autocity.core.world.WorldObject;
import autocity.core.world.resources.PineTree;

public class Grass extends Terrain {
    public Grass() {
        this.name = "Grass";
        this.character = '.';
        this.randomEntitySpawnRate = 0.25;
    }

    public WorldObject getRandomTerrainObject() {
        return new PineTree();
    }
}
