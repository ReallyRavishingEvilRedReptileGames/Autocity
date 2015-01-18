package autocity.core.factories;

import autocity.core.PlacementValidator;
import autocity.core.Settlement;
import autocity.core.World;
import autocity.core.civilians.prefabs.Villager;
import autocity.core.exceptions.BuildingConflictException;
import autocity.core.exceptions.PlacementAttemptsExceededException;
import autocity.core.exceptions.TerrainConflictException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.generators.builders.PathBuilder;
import autocity.core.generators.builders.SettlementBuilder;
import autocity.core.world.buildings.Hut;
import autocity.core.world.buildings.TownHall;
import autocity.core.world.buildings.prefabs.Building;

import java.util.Random;

public class SettlementFactory {
    private int placementAttempts = 50;
    private Settlement settlement;

    public int getPlacementAttempts() {
        return placementAttempts;
    }

    public void setPlacementAttempts(int placementAttempts) {
        this.placementAttempts = placementAttempts;
    }

    public Settlement generate(World world) throws PlacementAttemptsExceededException {
        settlement = new Settlement(world);

        place();
        addRoads();
        addBuilding(new TownHall());

        // Add 2-4 huts
        int hutCount = new Random().nextInt(2) + 2;

        for (int i = 0; i < hutCount; i++) {
            addBuilding(new Hut());
        }

        addPopulation();

        return settlement;
    }

    public void place() throws PlacementAttemptsExceededException {
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
            } catch (TerrainConflictException e) {
                System.out.println("Settlement cannot be placed on " + e.getTerrain());
            }
        }

        if (!placed) {
            System.out.println("Unable to place settlement, cancelling.");
            throw new PlacementAttemptsExceededException();
        }
    }

    private void addRoads() {
        PathBuilder builder = new PathBuilder(settlement.getWorld());

        try {
            builder.generateBetweenTiles(settlement.getOriginTile(), settlement.getOriginTile());
        } catch (TileOutOfBoundsException e) {
            System.out.println("Tile out of bounds!!!");
        }
    }

    private void addBuilding(Building building) {
        System.out.println("Adding building " + building);

        building.setSettlement(this.settlement);

        SettlementBuilder settlementBuilder = new SettlementBuilder(this.settlement);

        settlementBuilder.placeBuilding(building);
    }

    private void addPopulation() {
        int townCapacity = this.settlement.getPopulationCapacity();

        for (int i = 0; i < townCapacity; i++) {
            this.settlement.addCitizen(new Villager());
        }
    }
}
