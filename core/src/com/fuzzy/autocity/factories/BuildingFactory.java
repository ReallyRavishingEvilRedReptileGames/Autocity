package com.fuzzy.autocity.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.fuzzy.autocity.world.buildings.prefabs.*;

import java.util.ArrayList;

public class BuildingFactory {

    private ArrayList<Building> buildings;

    public static BuildingFactory initialize() {
        Json j = new Json();
        return j.fromJson(BuildingFactory.class, Gdx.files.internal("buildings.json"));
    }

    public Building create(String s) {
        for (Building b : buildings) {
            System.out.println(b.getWidth());
            if (s.equalsIgnoreCase(b.getName())) {
                if (b instanceof Civic) {
                    return new Civic((Civic) b);
                } else if (b instanceof Residential) {
                    return new Residential((Residential) b);
                } else if (b instanceof Special) {
                    return new Special((Special) b);
                } else {
                    return new Industrial((Industrial) b);
                }
            }
        }
        return null;
    }

    public ArrayList<Building> getList() {
        return this.buildings;
    }
}
