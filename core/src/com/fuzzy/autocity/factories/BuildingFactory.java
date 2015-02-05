package com.fuzzy.autocity.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.fuzzy.autocity.world.buildings.prefabs.Building;
import com.fuzzy.autocity.world.buildings.prefabs.Civic;
import com.fuzzy.autocity.world.buildings.prefabs.Residential;

import java.util.ArrayList;

public class BuildingFactory {

    private ArrayList<Building> buildings;

    public static BuildingFactory initialize() {
        Json j = new Json();
        BuildingFactory b = j.fromJson(BuildingFactory.class, Gdx.files.internal("buildings.json"));
        return b;
    }

    public Building create(String s) {
        Building returnable = null;
        for (Building b : buildings) {
            System.out.println(b.getWidth());
            if (s.equalsIgnoreCase(b.getName())) {
                    returnable = b;
            }
        }
        if (returnable instanceof Civic) {
            return new Civic((Civic)returnable);
        } else if (returnable instanceof Residential) {
            return new Residential((Residential) returnable);
        }
        return null;
    }


    public void read() {
        for (Building b : buildings) {
            System.out.println(b.getName());
        }
    }
}
