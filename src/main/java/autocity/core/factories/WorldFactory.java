package autocity.core.factories;

import autocity.core.Settlement;
import autocity.core.World;
import autocity.core.exceptions.PlacementAttemptsExceededException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.world.resources.Tree;

import java.util.Random;

public class WorldFactory {
    public static World generate() {
        World world = new World(48, 48);

        generateTerrain(world);
        generateFoliage(world);
        generateSettlements(world);

        return world;
    }

    private static void generateSettlements(World world) {
        for (int i = 0; i < 5; i++) {
            try {
                Settlement settlement = SettlementFactory.generate(world);
                world.addSettlement(settlement);
            } catch (PlacementAttemptsExceededException e) {
                //
            }
        }
    }

    private static void generateTerrain(World world) {
        //todo terrain generation
    }

    private static void generateFoliage(World world) {
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
