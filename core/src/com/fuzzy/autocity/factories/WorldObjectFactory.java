package com.fuzzy.autocity.factories;

import com.badlogic.gdx.utils.Json;
import com.fuzzy.autocity.World;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.buildings.prefabs.*;
import com.fuzzy.autocity.world.paths.prefabs.Path;
import com.fuzzy.autocity.world.resources.Resource;
import com.fuzzy.autocity.world.resources.prefabs.Tree;

import java.util.ArrayList;

public class WorldObjectFactory {
    // I don't know how to combine multiple json files into one array so I'm separating them for now.
    private ArrayList<Building> buildings;
    private ArrayList<Path> paths;
    private ArrayList<Resource> resources;

    public WorldObjectFactory() {
        buildings = BuildingFactory.initialize().getList();
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
        for (WorldObject w : buildings) {
            if (w.getName().equalsIgnoreCase(s)) {
                // Super ugly but sue me :^3
                if (w instanceof Civic) {
                    return new Civic((Civic) w);
                } else if (w instanceof Residential) {
                    return new Residential((Residential) w);
                } else if (w instanceof Research) {
                    return new Research((Research) w);
                } else {
                    return new Store((Store) w);
                }
            }
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
