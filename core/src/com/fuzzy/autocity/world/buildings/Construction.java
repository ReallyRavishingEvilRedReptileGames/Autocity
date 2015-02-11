package com.fuzzy.autocity.world.buildings;

import com.fuzzy.autocity.world.WorldObject;

public abstract class Construction extends WorldObject {
    protected double constructionSpeed = 0.05;
    protected double constructionTime = 0;
    protected int maxConstructionTime = 0;
    protected boolean constructed = false;

    public boolean isConstructed() {
        return this.constructed;
    }

    public double getConstructionSpeed() {
        return this.constructionSpeed;
    }

    public int getMaxConstructionTime() {
        return this.maxConstructionTime;
    }
}
