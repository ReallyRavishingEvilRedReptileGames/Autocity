package com.fuzzy.autocity.world.buildings.prefabs;

import com.fuzzy.autocity.world.WorldObject;

public abstract class Construction extends WorldObject {
    protected int constructionSpeed = 1;
    protected int constructionTime = 0;
    protected int maxConstructionTime = 0;
    protected boolean constructed = false;

    public boolean isConstructed() {
        return this.constructed;
    }

    public int getConstructionSpeed() {
        return this.constructionSpeed;
    }

    public int getMaxConstructionTime() {
        return this.maxConstructionTime;
    }
}
