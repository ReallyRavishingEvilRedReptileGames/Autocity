package com.fuzzy.autocity.factories;

import com.fuzzy.autocity.*;
import com.fuzzy.autocity.exceptions.PlacementAttemptsExceededException;
import com.fuzzy.autocity.generators.aStarPathFinder;
import com.fuzzy.autocity.generators.fractals.DiamondSquareFractal;
import com.fuzzy.autocity.terrain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO create anonymous functions to reduce copypasted nested for loops
public class WorldFactory {
    private int sizeX;
    private int sizeY;
    private World world;
    private int regenAttempts = 0;

    private double foliageRequiredFractalValue = 0.4;

    public double getFoliageRequiredFractalValue() {
        return foliageRequiredFractalValue;
    }

    public void setFoliageRequiredFractalValue(double foliageRequiredFractalValue) {
        this.foliageRequiredFractalValue = foliageRequiredFractalValue;
    }

    public World generate(int sizeX, int sizeY) {
        this.world = new World(sizeX, sizeY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        System.out.println("... Generating Terrain...");
        this.generateHeight();
        this.generateTerrain();
        System.out.println("... Generating Vegetation...");
        this.generateFoliage();
        System.out.println("... Generating Rivers...");
        this.generateRivers();
        try {
            this.generateSettlements();
        } catch (PlacementAttemptsExceededException e) {
            generate(sizeX, sizeY);
            regenAttempts++;
            System.out.print("Attempt #" + regenAttempts + " ");
        }
        System.out.println();
        return this.world;
    }

    private void generateSettlements() throws PlacementAttemptsExceededException {
        SettlementFactory settlementFactory = new SettlementFactory();

        for (Player p : Game.getPlayerList()) {
            Settlement settlement = settlementFactory.generate(world, p);
            world.addSettlement(settlement);
        }
    }

    private void generateHeight() {
        DiamondSquareFractal diamondSquareFractal = new DiamondSquareFractal();
        diamondSquareFractal.setRoughness(-0.01);
        diamondSquareFractal.setSize(Math.max(sizeX, sizeY));

        Double[][] map = diamondSquareFractal.generate();

        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                world.getTile(x, y).setHeight((int) (map[x][y] * 255));
            }
        }
    }

    private void generateTerrain() {
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                Tile tile = world.getTile(x, y);
                int height = tile.getHeight();

                if (height >= 250) {
                    tile.setTerrain(new Mountain());
                } else if (height >= 32) {
                    tile.setTerrain(new Grass());
                } else if (height >= 16) {
                    tile.setTerrain(new Sand());
                } else {
                    tile.setTerrain(new Water());
                }
            }
        }

    }

    private void generateRivers() {
        Random rand = new Random();
        aStarPathFinder generator = new aStarPathFinder(this.world, 256, true);
        List<Tile> source = new ArrayList<>();
        int failCount = 0;
        int maxTries = rand.nextInt(100);

        while (source.size() < maxTries) {
            Tile tile = world.getTile(rand.nextInt(world.getWidth()), rand.nextInt(world.getHeight()));
            if (!(tile.getTerrain() instanceof Mountain) && tile.getHeight() > 240) {
                source.add(tile);
                maxTries--;
            }
        }

        for (Tile sourceTile : source) {
            Tile last = null;
            List<Tile> visited = new ArrayList<>();
            try {
                for (Tile t : generator.generateRiver(sourceTile.getX(), sourceTile.getY())) {
                    setFlowDirection(last, t);
                    if (!(t.getTerrain() instanceof Water)) {
                        t.setTerrain(new River());
                    }
                    // Erosion or some such nonsense
                    if ((t.getTerrain() instanceof River)) {
                        t.setHeight(t.getHeight() - 5);
                        for (Tile neighbor : world.getNeighboringTiles(t)) {
                            if (!(neighbor.getTerrain() instanceof Water)) { // Don't modify water tile's height
                                neighbor.setHeight(neighbor.getHeight() - 1);
                            }
                            if (neighbor.getHeight() < t.getHeight() && !(neighbor.getTerrain() instanceof Water) && !visited.contains(neighbor)) {
                                if (neighbor.getOccupyingObject() != null) {
                                    neighbor.setOccupyingObject(null);
                                }
                                visited.add(neighbor);
                                neighbor.setTerrain(new RiverBank());
                            }
                            visited.clear();
                        }
                    }
                    last = t;
                }
                System.out.println("River generated!");
            } catch (NullPointerException n) {
                failCount++;
            }

        }
        if (source.size() == 0) {
            System.out.println("No rivers generated.");
        }

        System.out.println(failCount + " rivers failed to generate.");
    }

    private void setFlowDirection(Tile last, Tile current) {
        // Todo.
    }

    private void generateFoliage() {
        DiamondSquareFractal diamondSquareFractal = new DiamondSquareFractal();
        diamondSquareFractal.setRoughness(0.03);
        diamondSquareFractal.setSize(Math.max(this.sizeX, this.sizeY));

        Random random = new Random();
        WorldObjectFactory wof = new WorldObjectFactory();

        Double[][] map = diamondSquareFractal.generate();

        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {

                Tile tile = world.getTile(x, y);

                if ((map[x][y] > this.foliageRequiredFractalValue) && (random.nextDouble() <= tile.getTerrain().getRandomEntitySpawnRate())) {
                    tile.setOccupyingObject(wof.createWorldObject(tile.getTerrain().getRandomTerrainObject()));
                }
            }
        }
    }
}
