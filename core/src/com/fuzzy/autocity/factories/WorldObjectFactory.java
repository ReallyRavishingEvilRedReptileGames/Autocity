package com.fuzzy.autocity.factories;

import com.fuzzy.autocity.dictionary.WorldObjects;
import com.fuzzy.autocity.world.buildings.WorldObject;
import com.fuzzy.autocity.world.paths.prefabs.Path;
import com.fuzzy.autocity.world.resources.Resource;
import com.fuzzy.autocity.world.resources.prefabs.Tree;

import java.util.ArrayList;

public class WorldObjectFactory {
    // I don't know how to combine multiple json files into one array so I'm separating them for now.
    private ArrayList<Path> paths;
    private ArrayList<Resource> resources;

    public WorldObjectFactory() {
        paths = PathFactory.initialize().getList();
        resources = ResourceFactory.initialize().getList();
    }

    public WorldObject createBuilding() {
        return null;
    }

    public Path createPath() {
        return null;
    }

    public Tree createResource() {
        return null;
    }

    public com.fuzzy.autocity.world.WorldObject createWorldObject(String s) {
        com.fuzzy.autocity.world.WorldObject obj = WorldObjects.getInstance().create(s);

        if (obj != null) {
            return obj;
        }

        for (com.fuzzy.autocity.world.WorldObject w : paths) {
            if (w.getName().equalsIgnoreCase(s)) {
                return new Path((Path) w);
            }
        }

        for (com.fuzzy.autocity.world.WorldObject w : resources) {
            if (w.getName().equalsIgnoreCase(s)) {
                if (w instanceof Tree) {
                    return new Tree((Tree) w);
                }
            }
        }
        return null;
    }
}
