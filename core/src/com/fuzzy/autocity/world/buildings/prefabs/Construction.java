package com.fuzzy.autocity.world.buildings.prefabs;

import com.fuzzy.autocity.world.WorldObject;

public abstract class Construction extends WorldObject {
    protected float constructionSpeed = 0.1f;
    protected float constructionTime = 0;
    protected int maxConstructionTime = 0;
    protected boolean constructed = false;

    public boolean isConstructed() {
        return this.constructed;
    }

    public float getConstructionSpeed() {
        return this.constructionSpeed;
    }

    public int getMaxConstructionTime() {
        return this.maxConstructionTime;
    }
}
