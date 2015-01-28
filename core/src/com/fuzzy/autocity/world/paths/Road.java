package com.fuzzy.autocity.world.paths;

import com.fuzzy.autocity.world.paths.prefabs.Path;

public class Road extends Path {
    protected float constructionSpeed = 0.3f;
    protected float constructionTime = 0;
    protected int maxConstructionTime = 0;
    protected boolean constructed = false;

    public Road() {
        this.name = "Road";
        this.character = '=';
        this.maxConstructionTime = 1;
    }
}
