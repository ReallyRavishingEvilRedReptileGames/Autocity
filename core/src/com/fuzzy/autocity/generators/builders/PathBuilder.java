package com.fuzzy.autocity.generators.builders;

import com.fuzzy.autocity.Invokable;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.World;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.generators.aStarPathFinder;
import com.fuzzy.autocity.terrain.Water;
import com.fuzzy.autocity.world.paths.Road;

public class PathBuilder implements Invokable {
    private World world;
    private int range = 2;
    private int min = 2;
    private int defaultMaxSearchDistance = 100;

    public PathBuilder(World world) {
        this.world = world;
    }

    public void generateBetweenTiles(Tile tile1, Tile tile2) throws TileOutOfBoundsException {
        int xDiff = tile1.getX() - tile2.getX();
        int yDiff = tile1.getY() - tile2.getY();

        System.out.println("Generating between " + tile1.getX() + "," + tile1.getY() + " and " + tile2.getX() + "," + tile2.getY());

        if (xDiff == 0 && yDiff == 0) {
            System.out.println("Road complete.");
            //no further generation required
        } else if (Math.abs(xDiff) > Math.abs(yDiff)) {
            System.out.println("Going across x axis.");

            for (int x = tile1.getX(); x <= tile1.getX() + Math.abs(xDiff); x++) {
                if (isInvalidTile(this.world.getTile(x, tile1.getY()))) {
                    System.out.println("Placing road at " + x + "," + tile1.getY());
                    Road r = new Road();
                    this.world.buildConstruction(r, this.world.getTile(x, tile1.getY()));
                }
            }

            this.generateBetweenTiles(this.world.getTile(tile2.getX(), tile1.getY()), tile2);
        } else {
            System.out.println("Going across y axis.");

            for (int y = tile1.getY(); y <= tile1.getY() + Math.abs(yDiff); y++) {
                if (isInvalidTile(this.world.getTile(tile1.getX(), y))) {
                    System.out.println("Placing road at " + tile1.getX() + "," + y);
                    Road r = new Road();
                    this.world.buildConstruction(r, this.world.getTile(tile1.getX(), y));
                }
            }

            this.generateBetweenTiles(this.world.getTile(tile1.getX(), tile2.getY()), tile2);
        }
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

    private boolean isInvalidTile(Tile tile) {
        return !(tile.getTerrain() instanceof Water) || tile.getOccupyingObject() == null;
    }

    @Override
    public void Execute(String command) {
        String[] tmp = command.split(delimiter);
        switch (tmp[1]) {
            case "generate":
                try {
                    generate(world.getTile(Integer.valueOf(tmp[2]), Integer.valueOf(tmp[3])),
                            world.getTile(Integer.valueOf(tmp[4]), Integer.valueOf(tmp[5])));
                } catch (NumberFormatException | TileOutOfBoundsException ignored) {

                }
        }
    }
}
