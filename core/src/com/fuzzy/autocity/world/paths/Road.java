package com.fuzzy.autocity.world.paths;

import com.fuzzy.autocity.world.buildings.prefabs.Constructable;
import com.fuzzy.autocity.world.paths.prefabs.Path;

public class Road extends Path implements Constructable {
    protected float constructionSpeed = 0.3f;
    protected float constructionTime = 0;
    protected int maxConstructionTime = 0;
    protected boolean constructed = false;

    public Road() {
        this.name = "Road";
        this.character = '=';
        this.maxConstructionTime = 1;
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

    }

    @Override
    public boolean isConstructed() {
        return false;
    }

    @Override
    public char getCharacter() {
        float f = this.constructionTime * 10;
        return this.constructed ? this.character : java.lang.Character.forDigit((int) f, 10);
    }
}
