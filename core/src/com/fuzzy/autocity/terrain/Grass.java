package com.fuzzy.autocity.terrain;

import com.fuzzy.autocity.Terrain;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.resources.PineTree;

public class Grass extends Terrain {
    public Grass() {
        this.name = "Grass";
        this.character = '.';
        this.randomEntitySpawnRate = 0.25;
//        Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
//        p.setColor(Color.OLIVE);
//        p.fill();
//        this.texture = new Texture(p);
    }

    public WorldObject getRandomTerrainObject() {
        return new PineTree();
    }
}
