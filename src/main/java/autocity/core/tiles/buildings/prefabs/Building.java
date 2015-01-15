package autocity.core.tiles.buildings.prefabs;

import autocity.core.Person;
import autocity.core.Settlement;
import autocity.core.tiles.WorldObject;
import autocity.enums.EBuildingStyle;

public abstract class Building extends WorldObject {
    protected int constructionSpeed = 1;
    protected EBuildingStyle style = EBuildingStyle.Generic;
    protected int width = 3;
    protected int height = 2;
    protected Settlement settlement;
    protected Person owner;

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }
}
