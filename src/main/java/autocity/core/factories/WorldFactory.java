package autocity.core.factories;

import autocity.core.Settlement;
import autocity.core.World;
import autocity.core.exceptions.PlacementAttemptsExceededException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.generators.Fractal;
import autocity.core.world.resources.Tree;

import java.util.Random;

public class WorldFactory {
    private int size;

    public World generate(int size) {
        this.size = size;

        World world = new World(size, size);

        this.generateHeight(world);
        this.generateTerrain(world);
        this.generateFoliage(world);
        this.generateSettlements(world);

        return world;
    }

    private void generateSettlements(World world) {
        for (int i = 0; i < 5; i++) {
            try {
                Settlement settlement = SettlementFactory.generate(world);
                world.addSettlement(settlement);
            } catch (PlacementAttemptsExceededException e) {
                //
            }
        }
    }

    private void generateHeight(World world) {
        Fractal fractal = new Fractal();
        fractal.setRoughness(0.01);
        fractal.setSize(size);

        Double[][] map = fractal.generate();

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

    private void generateTerrain(World world) {
        //todo terrain generation
    }

    private void generateFoliage(World world) {
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
