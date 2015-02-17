package com.fuzzy.autocity.factories;

import com.fuzzy.autocity.PlacementValidator;
import com.fuzzy.autocity.Player;
import com.fuzzy.autocity.Settlement;
import com.fuzzy.autocity.World;
import com.fuzzy.autocity.civilians.prefabs.Villager;
import com.fuzzy.autocity.dictionary.Buildings;
import com.fuzzy.autocity.exceptions.BuildingConflictException;
import com.fuzzy.autocity.exceptions.PlacementAttemptsExceededException;
import com.fuzzy.autocity.exceptions.TerrainConflictException;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.generators.builders.PathBuilder;
import com.fuzzy.autocity.generators.builders.SettlementBuilder;
import com.fuzzy.autocity.world.buildings.Building;

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

    public Settlement generate(World world, Player p) throws PlacementAttemptsExceededException {
        settlement = new Settlement(world, p);
        Buildings bf = Buildings.getInstance();

        place();
        addRoads();
        addBuilding(bf.create("Town Hall"));

        // Add 2-4 huts
        int hutCount = new Random().nextInt(2) + 2;

        for (int i = 0; i < hutCount; i++) {
            addBuilding(bf.create("Hut"));

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
//                System.out.println("Settlement conflicts with " + e.getBuilding());
                // Settlement placement will conflict with a building
            } catch (TerrainConflictException e) {
//                System.out.println("Settlement cannot be placed on " + e.getTerrain());
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
            builder.generate(settlement.getOriginTile(), settlement.getOriginTile());
        } catch (TileOutOfBoundsException e) {
            System.out.println("Tile out of bounds!!!");
        }
    }

    private void addBuilding(Building building) {
        System.out.println("Adding building " + building);

        this.settlement.addBuilding(building);

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
