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

    public Resource create(String s) {
        for (Resource r : resources) {
            System.out.println(r.getWidth());
            if (s.equalsIgnoreCase(r.getName())) {
                if (r instanceof Tree) {
                    return new Tree((Tree) r);
                }
            }
        }
        return null;
    }

    public ArrayList<Resource> getList() {
        return this.resources;
    }
}
