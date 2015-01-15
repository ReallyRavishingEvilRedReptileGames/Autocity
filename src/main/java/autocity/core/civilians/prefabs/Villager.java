package autocity.core.civilians.prefabs;

import autocity.core.Character;
import autocity.core.Settlement;

public class Villager extends Character {
    protected Settlement settlement;

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        if (this.settlement != null) {
            this.settlement.removeCitizen(this);
        }

        this.settlement = settlement;
        this.settlement.addCitizen(this);
    }
}
