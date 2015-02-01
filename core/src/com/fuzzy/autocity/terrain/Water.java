package com.fuzzy.autocity.terrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.fuzzy.autocity.Terrain;
import com.fuzzy.autocity.world.WorldObject;

public class Water extends Terrain {
    public Water() {
        this.name = "Water";
        this.character = '~';
        this.randomEntitySpawnRate = -1;
        Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        p.setColor(Color.NAVY);
        p.fill();
        this.texture = new Texture(p);
    }

    public WorldObject getRandomTerrainObject() {
        return null;
    }
}
