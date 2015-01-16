package autocity.core.factories;

import autocity.core.Settlement;
import autocity.core.World;
import autocity.core.exceptions.PlacementAttemptsExceededException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.generators.fractals.DiamondSquareFractal;
import autocity.core.world.resources.Tree;

import java.util.Random;

public class WorldFactory {
    private int size;
    private World world;

    public World generate(int size) {
        this.world = new World(size, size);

        this.size = size;

        this.generateHeight();
        this.generateTerrain();
        this.generateFoliage();
        this.generateSettlements();

        return this.world;
    }

    private void generateSettlements() {
        for (int i = 0; i < 5; i++) {
            try {
                Settlement settlement = SettlementFactory.generate(world);
                world.addSettlement(settlement);
            } catch (PlacementAttemptsExceededException e) {
                //
            }
        }
    }

    private void generateHeight() {
        DiamondSquareFractal diamondSquareFractal = new DiamondSquareFractal();
        diamondSquareFractal.setRoughness(0.01);
        diamondSquareFractal.setSize(size);

        Double[][] map = diamondSquareFractal.generate();

        for (int i = 0; i < world.getWidth(); i++) {
            for (int j = 0; j < world.getHeight(); j++) {
                try {
                    world.getTile(i, j).setHeight((int) (map[i][j] * 255));
                } catch (TileOutOfBoundsException e) {
                    // Nah
                }
            }
        }
    }

    private void generateTerrain() {
        //todo terrain generation
    }

    private void generateFoliage() {
        Random random = new Random();

        for (int i = 0; i < world.getWidth(); i++) {
            for (int j = 0; j < world.getHeight(); j++) {
                try {
                    if (random.nextInt(50) == 1) {
                        world.getTile(i, j).setOccupyingObject(new Tree());
                    }
                } catch (TileOutOfBoundsException e) {
                    // not gonna happen ever
                }
            }
        }
    }
}
