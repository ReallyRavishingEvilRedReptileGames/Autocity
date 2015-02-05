package com.fuzzy.autocity.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.fuzzy.autocity.world.resources.Resource;
import com.fuzzy.autocity.world.resources.prefabs.Tree;

import java.util.ArrayList;

public class ResourceFactory {

    private ArrayList<Resource> resources;

    public static ResourceFactory initialize() {
        Json loader = new Json();
        return loader.fromJson(ResourceFactory.class, Gdx.files.internal("resources.json"));
    }

    public ArrayList<Resource> getList() {
        return this.resources;
    }
}
