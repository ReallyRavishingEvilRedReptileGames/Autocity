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

    public ArrayList<Path> getList() {
        return this.paths;
    }
}
