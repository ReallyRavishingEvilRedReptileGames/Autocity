package com.fuzzy.autocity.dictionary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.fuzzy.autocity.world.buildings.Building;
import java.util.HashMap;

public class Buildings {

    private HashMap<String, Building> buildings;

    private static Buildings obj;

    public static Buildings getInstance() {
        if (obj == null) {
            obj = (new Json()).fromJson(Buildings.class, Gdx.files.internal("buildings.json"));
        }

        return obj;
    }

    private Buildings() {
    }

    public Building create(String s) {
        if (buildings.containsKey(s)) {
            return new Building(buildings.get(s));
        }

        return null;
    }

    public HashMap<String, Building> getList() {
        return this.buildings;
    }
}

