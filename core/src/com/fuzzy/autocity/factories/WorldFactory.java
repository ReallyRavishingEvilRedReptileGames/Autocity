package com.fuzzy.autocity.factories;

import com.fuzzy.autocity.*;
import com.fuzzy.autocity.exceptions.PlacementAttemptsExceededException;
import com.fuzzy.autocity.generators.aStarPathFinder;
import com.fuzzy.autocity.generators.fractals.DiamondSquareFractal;
import com.fuzzy.autocity.terrain.Grass;
import com.fuzzy.autocity.terrain.Sand;
import com.fuzzy.autocity.terrain.Water;

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

        this.generateHeight();
        this.generateTerrain();
        this.generateRivers();
        this.generateFoliage();
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

    private void generateRivers() {
        Random rand = new Random();
        aStarPathFinder generator = new aStarPathFinder(this.world, 1000, true);
        List<Tile> source = new ArrayList<>();
        List<Tile> target = new ArrayList<>();
        for (int x = 0; x < 5; x++) { // 5 chances to create source tiles for rivers, then abort.
            Tile tile = world.getTile(rand.nextInt(world.getWidth()), rand.nextInt(world.getHeight()));
            if (tile.getHeight() > 64 && !source.contains(tile)) {
                source.add(tile);
            }
        }

        while (target.size() != source.size()) { // Match target list amount with source list amount.
            Tile tile = world.getTile(rand.nextInt(world.getWidth()), rand.nextInt(world.getHeight()));
            if (tile.getHeight() < 16 && !target.contains(tile)) {
                target.add(tile);
            }
        }

        for (int i = 0; i < source.size(); i++) {
            Tile sourceTile = source.get(i);
            Tile targetTile = target.get(i);
            try {
                for (Tile t : generator.findPath(sourceTile.getX(), sourceTile.getY(), targetTile.getX(), targetTile.getY())) {
                    if (!(t.getTerrain() instanceof Water)) {
                        t.setTerrain(new Water());
                    }
                }
            } catch (NullPointerException n) {
                System.out.println("No river generated! (wait how?)");
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
