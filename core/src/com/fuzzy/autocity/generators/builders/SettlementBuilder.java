package com.fuzzy.autocity.generators.builders;

import com.fuzzy.autocity.PlacementValidator;
import com.fuzzy.autocity.Settlement;
import com.fuzzy.autocity.enumeration.EDirection;
import com.fuzzy.autocity.exceptions.TerrainConflictException;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.exceptions.WorldObjectConflictException;
import com.fuzzy.autocity.world.buildings.prefabs.Building;

/**
 * A builder class designed to
 */
public class SettlementBuilder {
    private Settlement settlement;
    private PlacementValidator placementValidator;

    public SettlementBuilder(Settlement settlement) {
        this.settlement = settlement;
        this.placementValidator = new PlacementValidator(this.settlement.getWorld());
    }

    /**
     * Automatically place a building in a settlement.
     *
     * @param building
     */
    public void placeBuilding(Building building) {
        // In a spiral fashion, find the best place for this building.
        int spiralStep = 0;
        int spiralSteps = 1;
        int targetX = settlement.getOriginX();
        int targetY = settlement.getOriginY();
        EDirection direction = EDirection.East;
        boolean placed = false;

        PlacementValidator validator = new PlacementValidator(this.settlement.getWorld());

        while (!placed) {
            System.out.printf("Test placement of %s at %s,%s\n", building, targetX, targetY);

            try {
                validator.validateBuilding(building, targetX, targetY);
                this.settlement.getWorld().placeConstructable(building, this.settlement.getWorld().getTile(targetX, targetY));
                placed = true;
            } catch (TileOutOfBoundsException e) {
                System.out.println("Building out of map range.");
            } catch (TerrainConflictException e) {
                System.out.println("Building conflicts with invalid terrain.");
            } catch (WorldObjectConflictException e) {
                System.out.println("Building conflicts with other world object.");
            }

            spiralStep++;

            switch (direction) {
                case North:
                    targetY--;
                    break;
                case West:
                    targetX--;
                    break;
                case East:
                    targetX++;
                    break;
                case South:
                    targetY++;
                    break;
            }

            if (spiralStep == spiralSteps) {
                direction = direction.getClockwiseRotation();
                spiralStep = 0;
                spiralSteps++;
            }
        }
    }

    /**
     * Determine what building the settlement needs the most, then place it.
     */
    public void placeRecommendedBuilding() {
        // todo
    }

    /**
     * Automatically connect placed buildings to another, or start branching out.
     */
    public void placePaths() {
        // todo
    }
}
