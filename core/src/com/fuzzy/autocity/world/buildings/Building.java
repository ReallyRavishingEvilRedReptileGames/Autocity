package com.fuzzy.autocity.world.buildings;

import com.fuzzy.autocity.Character;
import com.fuzzy.autocity.Invokable;
import com.fuzzy.autocity.Settlement;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.enumeration.EBuildingStyle;
import com.fuzzy.autocity.enumeration.EDirection;

import java.util.ArrayList;

public class Building extends Construction {

    protected EBuildingStyle style = EBuildingStyle.Generic;
    protected Settlement settlement;
    protected Character owner;
    //protected EArchetype archetype;
    //protected int baseCapacity;

    public Building() {

    }

    public Building(Building b) {
        this.name = b.getName();
        this.height = b.getHeight();
        this.width = b.getWidth();
        this.character = b.getCharacter();
        this.constructionDuration = b.getConstructionDuration();
        //this.archetype = b.archetype;
        //this.baseCapacity = b.getBaseCapacity();
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
        this.player = settlement.getPlayer();
    }

    @Override
    @Invokable
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

    public boolean hasArchetype(EArchetype archetype) {
        return true; //todo
    }

    public int getBaseCapacity() {
        //return baseCapacity;
        return 0;
    }
}
