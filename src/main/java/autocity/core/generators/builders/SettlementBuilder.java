package autocity.core.generators.builders;

import autocity.core.PlacementValidator;
import autocity.core.Settlement;
import autocity.core.enumeration.EDirection;
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

    }

    /**
     * Determine what building the settlement needs the most, then place it.
     */
    public void placeRecommendedBuilding() {
        //todo
    }

    /**
     * Automatically connect placed buildings to another, or start branching out.
     */
    public void placePaths() {
        //todo
    }
}
