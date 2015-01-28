package com.fuzzy.autocity.world.buildings.prefabs;

import com.fuzzy.autocity.Character;
import com.fuzzy.autocity.Settlement;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.enumeration.EBuildingStyle;
import com.fuzzy.autocity.world.Constructable;

public abstract class Building extends Constructable {

    protected EBuildingStyle style = EBuildingStyle.Generic;
    protected int width = 3;
    protected int height = 2;
    protected Settlement settlement;
    protected Character owner;

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    @Override
    public void destroy() {
        for (Tile tile : tiles) {
            tile.setOccupyingObject(null);
        }

        if (this.settlement != null) { // We can't assume every building will belong to a settlement...
            this.settlement.removeBuilding(this);
            this.settlement = null;
        }

        //this.owner.removeBuilding(this);
        this.owner = null;

        this.onDestroy();
    }
}
