package com.fuzzy.autocity.world.buildings;

import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.world.Construction;
import com.fuzzy.autocity.world.buildings.prefabs.Building;

import java.util.HashSet;

public class GenericConstruction extends Construction {
    private Construction construction;

    //TODO: Pass the GenericConstruction tiles hashset to the
    //TODO: construction object in a way that doesn't fuck over the construction object.
    public GenericConstruction(Construction c) {
        this.construction = c;
        this.width = c.getWidth();
        this.height = c.getHeight();
        this.constructionSpeed = c.getConstructionSpeed();
        this.maxConstructionTime = c.getMaxConstructionTime();
    }


    public void Construct() {
        if (this.constructionTime < this.maxConstructionTime) {
            this.constructionTime += constructionSpeed * 1;
        } else {
            this.constructed = true;
        }

    }

    public void deConstruct() {
        if (this.constructionTime < this.maxConstructionTime) {
            this.constructionTime -= constructionSpeed * 1;
        } else {
            this.constructed = false;
            this.destroy();
        }
    }

    public Construction getConstruction() {
        return this.construction;
    }

}
