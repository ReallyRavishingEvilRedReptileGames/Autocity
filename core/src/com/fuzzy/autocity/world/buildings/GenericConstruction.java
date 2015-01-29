package com.fuzzy.autocity.world.buildings;

import com.fuzzy.autocity.world.buildings.prefabs.Construction;

public class GenericConstruction extends Construction {
    private Construction construction;

    public GenericConstruction(Construction c) {
        this.construction = c;
        this.width = c.getWidth();
        this.height = c.getHeight();
        this.constructionSpeed = c.getConstructionSpeed();
        this.maxConstructionTime = c.getMaxConstructionTime();
        this.tiles.forEach(construction::addTile);
        this.character = '%';
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

    @Override
    public char getCharacter() {
        float f = this.constructionTime * 10;
        return this.constructed ? this.character : java.lang.Character.forDigit((int) f, 10);
    }

    public Construction getConstruction() {
        return this.construction;
    }

}
