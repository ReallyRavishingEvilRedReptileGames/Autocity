package com.fuzzy.autocity.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.fuzzy.autocity.world.paths.prefabs.Path;

import java.util.ArrayList;

public class PathFactory {

    private ArrayList<Path> paths;

    public static PathFactory initialize() {
        Json loader = new Json();
        return loader.fromJson(PathFactory.class, Gdx.files.internal("paths.json"));
    }

    public Path create(String s) {
        for (Path p : paths) {
            if (p.getName().equalsIgnoreCase(s)) {
                return new Path(p);
            }
        }
        return null;
    }

    public ArrayList<Path> getList() {
        return this.paths;
    }
}
