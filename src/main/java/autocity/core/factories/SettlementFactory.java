package autocity.core.factories;

import autocity.core.ConstructionManager;
import autocity.core.Settlement;
import autocity.core.exceptions.PlacementAttemptsExceededException;
import autocity.core.generators.RoadBuilder;
import autocity.core.tiles.buildings.Hut;
import autocity.core.tiles.buildings.TownHall;
import autocity.core.tiles.buildings.prefabs.Building;

import java.util.Random;

public class SettlementFactory {
    private Settlement settlement;

    public SettlementFactory(Settlement settlement) {
        this.settlement = settlement;
    }

    public void generate() {
        this.addRoads();

        this.addBuilding(new TownHall());

        // Add 2-4 huts
        int hutCount = new Random().nextInt(2) + 2;

        for (int i = 0; i < hutCount; i++) {
            this.addBuilding(new Hut());
        }

        this.addPopulation();
    }

    private void addRoads() {
        RoadBuilder builder = new RoadBuilder(this.settlement);
        builder.generateStartingRoads();
    }

    private void addBuilding(Building building) {
        building.setSettlement(this.settlement);

        ConstructionManager manager = new ConstructionManager(this.settlement.getWorld(), building);

        manager.setSettlement(this.settlement);
        manager.setBuilding(building);

        try {
            manager.construct();
            this.settlement.addBuilding(building);
        } catch (PlacementAttemptsExceededException e) {
            //
        }
    }

    private void addPopulation() {

    }
}
