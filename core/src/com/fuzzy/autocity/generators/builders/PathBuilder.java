package com.fuzzy.autocity.generators.builders;

import com.fuzzy.autocity.Invokable;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.World;
import com.fuzzy.autocity.factories.WorldObjectFactory;
import com.fuzzy.autocity.generators.aStarPathFinder;
import com.fuzzy.autocity.world.buildings.Construction;

import java.util.Arrays;

@Invokable
public class PathBuilder {
    private World world;
    private int range = 2;
    private int min = 2;
    private int defaultMaxSearchDistance = 100;
    private WorldObjectFactory wof;

    public PathBuilder(World world) {
        this.world = world;
        wof = new WorldObjectFactory();
    }

    public void generate(Tile startTile, Tile targetTile, int maxSearchDistance) {
        aStarPathFinder f = new aStarPathFinder(this.world, maxSearchDistance);
        try {
            for (Tile t : f.findPath(startTile.getX(), startTile.getY(), targetTile.getX(), targetTile.getY())) {
                this.world.buildConstruction((Construction) wof.createWorldObject("road"), t);
            }
        } catch (NullPointerException npe) {
            System.out.println("No path generated!");
        }
    }

    public void generate(Tile startTile, Tile targetTile) {
        aStarPathFinder f = new aStarPathFinder(world, defaultMaxSearchDistance);
        try {
            for (Tile t : f.findPath(startTile.getX(), startTile.getY(), targetTile.getX(), targetTile.getY())) {
                if (t.getOccupyingObject() == null) {
                    this.world.buildConstruction((Construction) wof.createWorldObject("road"), t);
                }
            }
        } catch (NullPointerException npe) {
            System.out.println("No path generated!");
        }
    }

}
