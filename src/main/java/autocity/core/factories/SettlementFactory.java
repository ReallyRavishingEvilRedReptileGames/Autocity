package autocity.core.factories;

import autocity.core.ConstructionManager;
import autocity.core.PlacementValidator;
import autocity.core.Settlement;
import autocity.core.World;
import autocity.core.exceptions.BuildingConflictException;
import autocity.core.exceptions.PlacementAttemptsExceededException;
import autocity.core.generators.RoadBuilder;
import autocity.core.world.buildings.Hut;
import autocity.core.world.buildings.TownHall;
import autocity.core.world.buildings.prefabs.Building;

import java.util.Random;

public class SettlementFactory {
    private static int placementAttempts = 5;

    public static Settlement generate(World world) throws PlacementAttemptsExceededException {
        Settlement settlement = new Settlement(world);

        place(settlement);
        addRoads(settlement);
        addBuilding(settlement, new TownHall());

        // Add 2-4 huts
        int hutCount = new Random().nextInt(2) + 2;

        for (int i = 0; i < hutCount; i++) {
            addBuilding(settlement, new Hut());
        }

        addPopulation(settlement);

        return settlement;
    }

    public static void place(Settlement settlement) throws PlacementAttemptsExceededException {
        int width = settlement.getWorld().getWidth();
        int height = settlement.getWorld().getHeight();
        boolean placed = false;

        Random rand = new Random();

        PlacementValidator validator = new PlacementValidator(settlement.getWorld());

        for (int i = 0; i < placementAttempts; i++) {
            int nextX = rand.nextInt(width);
            int nextY = rand.nextInt(height);

            try {
                validator.validateSettlement(settlement, nextX, nextY);
                settlement.setOriginX(nextX);
                settlement.setOriginY(nextY);
                placed = true;
                System.out.println("Placed settlement at (" + settlement.getOriginX() + "," + settlement.getOriginY() + ")");
                break;
            } catch (BuildingConflictException e) {
                System.out.println("Settlement conflicts with " + e.getBuilding());
                // Settlement placement will conflict with a building
            }
        }

        if (!placed) {
            System.out.println("Unable to place settlement, cancelling.");
            throw new PlacementAttemptsExceededException();
        }
    }

    private static void addRoads(Settlement settlement) {
        RoadBuilder builder = new RoadBuilder(settlement);
        builder.generateStartingRoads();
    }

    private static void addBuilding(Settlement settlement, Building building) {
        building.setSettlement(settlement);

        ConstructionManager manager = new ConstructionManager(settlement.getWorld(), building);

        manager.setSettlement(settlement);
        manager.setBuilding(building);

        try {
            manager.construct();
            settlement.addBuilding(building);
        } catch (PlacementAttemptsExceededException e) {
            //
        }
    }

    private static void addPopulation(Settlement settlement) {

    }
}
