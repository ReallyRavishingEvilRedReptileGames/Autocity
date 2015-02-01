package com.fuzzy.autocity.terrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.fuzzy.autocity.Terrain;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.resources.PalmTree;

public class Sand extends Terrain {
    public Sand() {
        this.name = "Sand";
        this.character = ',';
        this.randomEntitySpawnRate = 0.05;
        Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        p.setColor(Color.ORANGE);
        p.fill();
        this.texture = new Texture(p);
    }

    public WorldObject getRandomTerrainObject() {
        return new PalmTree();
    }
}
