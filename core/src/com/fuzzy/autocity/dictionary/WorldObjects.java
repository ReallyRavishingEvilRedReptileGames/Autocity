package com.fuzzy.autocity.dictionary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.fuzzy.autocity.world.buildings.WorldObject;
import java.util.HashMap;

public class WorldObjects {

    private HashMap<String, WorldObject> worldObjects;

    private static WorldObjects obj;

    public static WorldObjects getInstance() {
        if (obj == null) {
            obj = (new Json()).fromJson(WorldObjects.class, Gdx.files.internal("buildings.json"));
        }

        return obj;
    }

    private WorldObjects() {
    }

    public WorldObject create(String s) {
        if (worldObjects.containsKey(s)) {
            return new WorldObject(worldObjects.get(s));
        }

        return null;
    }

    public HashMap<String, WorldObject> getList() {
        return this.worldObjects;
    }
}

