package com.fuzzy.autocity.factories;

import com.fuzzy.autocity.dictionary.Buildings;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.buildings.Building;
import com.fuzzy.autocity.world.paths.prefabs.Path;
import com.fuzzy.autocity.world.resources.Resource;
import com.fuzzy.autocity.world.resources.prefabs.Tree;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldObjectFactory {
    // I don't know how to combine multiple json files into one array so I'm separating them for now.
    private ArrayList<Path> paths;
    private ArrayList<Resource> resources;

    public WorldObjectFactory() {
        paths = PathFactory.initialize().getList();
        resources = ResourceFactory.initialize().getList();
    }

    public Building createBuilding() {
        return null;
    }

    public Path createPath() {
        return null;
    }

    public Tree createResource() {
        return null;
    }

    public WorldObject createWorldObject(String s) {
        WorldObject obj = Buildings.getInstance().create(s);

        if (obj != null) {
            return obj;
        }

        for (WorldObject w : paths) {
            if (w.getName().equalsIgnoreCase(s)) {
                return new Path((Path) w);
            }
        }

        for (WorldObject w : resources) {
            if (w.getName().equalsIgnoreCase(s)) {
                if (w instanceof Tree) {
                    return new Tree((Tree) w);
                }
            }
        }
        return null;
    }
}
