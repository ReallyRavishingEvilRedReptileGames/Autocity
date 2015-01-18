package autocity.core.generators.builders;

import autocity.core.PlacementValidator;
import autocity.core.Settlement;
import autocity.core.enumeration.EDirection;
import autocity.core.exceptions.TerrainConflictException;
import autocity.core.exceptions.TileOutOfBoundsException;
import autocity.core.exceptions.WorldObjectConflictException;
import autocity.core.world.buildings.prefabs.Building;

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
                building.placeAt(this.settlement.getWorld(), targetX, targetY);
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
