package com.fuzzy.autocity.generators.builders;

import com.fuzzy.autocity.Invokable;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.World;
import com.fuzzy.autocity.generators.aStarPathFinder;
import com.fuzzy.autocity.world.paths.Road;

public class PathBuilder implements Invokable {
    private World world;
    private int range = 2;
    private int min = 2;
    private int defaultMaxSearchDistance = 100;

    public PathBuilder(World world) {
        this.world = world;
    }

    public void generate(Tile startTile, Tile targetTile, int maxSearchDistance) {
        aStarPathFinder f = new aStarPathFinder(this.world, maxSearchDistance);
        try {
            for (Tile t : f.findPath(startTile.getX(), startTile.getY(), targetTile.getX(), targetTile.getY())) {
                Road r = new Road();
                this.world.buildConstruction(r, t);
            }
        } catch (NullPointerException npe) {
            System.out.println("No path generated!");
        }
    }

    public void generate(Tile startTile, Tile targetTile) {
        aStarPathFinder f = new aStarPathFinder(world, defaultMaxSearchDistance);
        try {
            for (Tile t : f.findPath(startTile.getX(), startTile.getY(), targetTile.getX(), targetTile.getY())) {
                Road r = new Road();
                this.world.buildConstruction(r, t);
            }
        } catch (NullPointerException npe) {
            System.out.println("No path generated!");
        }
    }

    @Override
    public void Execute(String command) {
        String[] tmp = command.split(delimiter);
        switch (tmp[1]) {
            case "generate":
                try {
                    generate(world.getTile(Integer.valueOf(tmp[2]), Integer.valueOf(tmp[3])),
                            world.getTile(Integer.valueOf(tmp[4]), Integer.valueOf(tmp[5])));
                } catch (NumberFormatException ignored) {
                }
        }
    }
}
