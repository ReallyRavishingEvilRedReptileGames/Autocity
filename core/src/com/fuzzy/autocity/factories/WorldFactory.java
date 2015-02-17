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
                } else if (height >= 64) {
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
        aStarPathFinder generator = new aStarPathFinder(this.world, 1000, true);
        List<Tile> source = new ArrayList<>();
        int sourceCount = 0;
        int maxTries = 50;

        for (int x = 0; x < maxTries; x++) {
            Tile tile = world.getTile(rand.nextInt(world.getWidth()), rand.nextInt(world.getHeight()));
            if (!(tile.getTerrain() instanceof Mountain) && tile.getHeight() > 164) {
                source.add(tile);
//                maxTries--;
            }
        }


        for (Tile sourceTile : source) {
            try {
                for (Tile t : generator.generateRiver(sourceTile.getX(), sourceTile.getY())) {
                    if (!(t.getTerrain() instanceof River)) {
                        t.setTerrain(new River());
                        if (t.getTerrain() instanceof Water) {
                            // don't change height
                        } else {
                            t.setHeight(t.getHeight() - 5);
                        }
                        System.out.println(t.getHeight());
                    }
                }
                System.out.println("River generated!");
            } catch (NullPointerException n) {
                System.out.println("River generation failed! @" + sourceCount);

            }
            sourceCount++;
        }
        if (source.size() == 0) {
            System.out.println("No rivers generated.");
        }
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
