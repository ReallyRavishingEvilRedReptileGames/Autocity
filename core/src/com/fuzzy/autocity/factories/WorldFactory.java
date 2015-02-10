package com.fuzzy.autocity.factories;

import com.fuzzy.autocity.*;
import com.fuzzy.autocity.exceptions.PlacementAttemptsExceededException;
import com.fuzzy.autocity.generators.fractals.DiamondSquareFractal;
import com.fuzzy.autocity.terrain.Grass;
import com.fuzzy.autocity.terrain.Sand;
import com.fuzzy.autocity.terrain.Water;

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

        this.generateHeight();
        this.generateTerrain();
        this.generateFoliage();
        try {
            this.generateSettlements();
        } catch (PlacementAttemptsExceededException e) {
            generate(sizeX, sizeY);
            regenAttempts++;
            System.out.print("Attempt #" + regenAttempts + " ");
        }

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
        diamondSquareFractal.setRoughness(0.03);
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

                if (height >= 64) {
                    tile.setTerrain(new Grass());
                } else if (height >= 16) {
                    tile.setTerrain(new Sand());
                } else {
                    tile.setTerrain(new Water());
                }
            }
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
