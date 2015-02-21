package com.fuzzy.autocity.generators.builders;

import com.fuzzy.autocity.Invokable;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.World;
import com.fuzzy.autocity.enumeration.EDirection;
import com.fuzzy.autocity.generators.aStarPathFinder;
import com.fuzzy.autocity.terrain.River;
import com.fuzzy.autocity.terrain.RiverBank;
import com.fuzzy.autocity.terrain.Water;

import java.util.ArrayList;
import java.util.List;

public class RiverBuilder {

    private World world;

    public RiverBuilder(World world) {
        this.world = world;
    }

    public void generate(Tile t) {
        generate(t.getX(), t.getY());
    }

    @Invokable
    public void generate(int startX, int startY) {
        aStarPathFinder generator = new aStarPathFinder(this.world, 256, true);

        Tile last = null;
        List<Tile> visited = new ArrayList<>();
        try {
            for (Tile t : generator.generateRiver(startX, startY)) {
                setFlowDirection(last, t);
                if (!(t.getTerrain() instanceof Water)) {
                    t.setTerrain(new River());
                }
                if (last != null && last.getHeight() < t.getHeight()) {
                    t.setHeight(last.getHeight());
                }
                // Eroding the river's banks
                int lowestTile = t.getHeight();
                if ((t.getTerrain() instanceof River)) {
                    for (Tile neighbor : world.getNeighboringTiles(t)) {
                        if (!(neighbor.getTerrain() instanceof Water)) { // Don't modify water tile's height
                            neighbor.setHeight(neighbor.getHeight() - 1);
                            if (neighbor.getHeight() < lowestTile) {
                                lowestTile = neighbor.getHeight();
                            }
                        }
                        if (neighbor.getHeight() < t.getHeight()
                                && !(neighbor.getTerrain() instanceof Water)
                                && !visited.contains(neighbor)) {
                            if (neighbor.getOccupyingObject() != null) {
                                neighbor.setOccupyingObject(null);
                            }
                            visited.add(neighbor);
                            neighbor.setTerrain(new RiverBank());
                        }
                        visited.clear();
                    }
                }
                t.setHeight( lowestTile - 1);
                last = t;
            }
            System.out.println("River generated!");
        } catch (NullPointerException ignored) {
            System.out.println("River failed to generate.");
        }
    }


    private void setFlowDirection(Tile last, Tile current) {
        /*
        0, -1 North
        0, +1 South
        -1, 0 West
        +1, 0 East
        Then we set it as the opposite.
        */
        if (world.getTile(current.getX(), current.getY() - 1).equals(last)) {
            River r = (River) last.getTerrain();
            r.setFlowDirection(EDirection.South);
        } else if (world.getTile(current.getX(), current.getY() + 1).equals(last)) {
            River r = (River) last.getTerrain();
            r.setFlowDirection(EDirection.North);
        } else if (world.getTile(current.getX() - 1, current.getY()).equals(last)) {
            River r = (River) last.getTerrain();
            r.setFlowDirection(EDirection.East);
        } else if (world.getTile(current.getX() + 1, current.getY()).equals(last)) {
            River r = (River) last.getTerrain();
            r.setFlowDirection(EDirection.West);
        }
    }
}
