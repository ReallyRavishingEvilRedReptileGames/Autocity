package com.fuzzy.autocity.world.buildings.prefabs;

import com.fuzzy.autocity.Character;
import com.fuzzy.autocity.Settlement;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.enumeration.EBuildingStyle;
import com.fuzzy.autocity.world.WorldObject;

public abstract class Building extends WorldObject implements Constructable {
    protected float constructionSpeed = 0.1f;
    protected float constructionTime = 0;
    protected int maxConstructionTime = 0;
    protected boolean constructed = false;
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


    @Override
    public void Construct() {
        if (this.constructionTime < this.maxConstructionTime) {
            this.constructionTime += constructionSpeed * 1;
        } else {
            this.constructed = true;

        }

    }

    @Override
    public void deConstruct() {
        if (this.constructionTime < this.maxConstructionTime) {
            this.constructionTime -= constructionSpeed * 1;
        } else {
            this.constructed = false;
            this.destroy();
        }
    }

    @Override
    public char getCharacter() {
        if (this.constructed) {
            return this.character;
        } else {
            return java.lang.Character.forDigit((int) this.constructionTime, 10);
        }
    }

    @Override
    public boolean isConstructed() {
        return this.constructed;
    }

}
