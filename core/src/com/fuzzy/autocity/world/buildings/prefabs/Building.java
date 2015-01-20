package com.fuzzy.autocity.world.buildings.prefabs;

import com.fuzzy.autocity.Character;
import com.fuzzy.autocity.Settlement;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.enumeration.EBuildingStyle;
import com.fuzzy.autocity.world.WorldObject;

public abstract class Building extends WorldObject {
    protected int constructionSpeed = 1;
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

        this.settlement.removeBuilding(this);
        this.settlement = null;

        //this.owner.removeBuilding(this);
        this.owner = null;

        this.onDestroy();
    }

}
