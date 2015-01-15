package autocity.core.factories;

import autocity.core.Settlement;
import autocity.core.World;
import autocity.core.exceptions.PlacementAttemptsExceededException;

public class WorldFactory {
    public static World generate() {
        World world = new World(48, 48);

        generateTerrain(world);
        generateSettlements(world);

        return world;
    }

    public static void generateSettlements(World world) {
        for (int i = 0; i < 5; i++) {
            Settlement settlement = new Settlement(world);

            try {
                settlement.autoPlace();
                settlement.found();
                world.addSettlement(settlement);
            } catch (PlacementAttemptsExceededException e) {
                //
            }
        }
    }

    public static void generateTerrain(World world) {
        //todo
    }
}
