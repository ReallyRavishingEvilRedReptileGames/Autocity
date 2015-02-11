package com.fuzzy.autocity.world.buildings;

import com.fuzzy.autocity.world.WorldObject;

public abstract class Construction extends WorldObject {
    protected final double constructionSpeed = 0.05;
    protected double constructionProgress = 0;
    protected int constructionDuration = 0; // In seconds
    protected boolean constructed = false;

    public boolean isConstructed() {
        return this.constructed;
    }

    public int getConstructionDuration() {
        return this.constructionDuration;
    }
}
