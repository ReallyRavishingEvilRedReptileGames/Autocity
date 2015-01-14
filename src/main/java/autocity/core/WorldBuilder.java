package autocity.core;

import autocity.exceptions.PlacementAttemptsExceededException;

public class WorldBuilder {
    private Map map;

    /**
     * Generates a map.
     */
    public Map generate() {
        this.map = new Map(48, 48);

        this.generateTerrain();
        this.generateSettlements();

        return this.map;
    }

    public void generateSettlements() {
        for (int i = 0; i < 5; i++) {
            Settlement settlement = new Settlement(this.map);

            try {
                settlement.autoPlace();
                settlement.generateStarter();
                this.map.addSettlement(settlement);
            } catch (PlacementAttemptsExceededException e) {
                //
            }
        }
    }

    public void generateTerrain() {
        //todo
    }
}
