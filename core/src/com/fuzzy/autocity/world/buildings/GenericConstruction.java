package com.fuzzy.autocity.world.buildings;

public class GenericConstruction extends Construction {
    private Construction construction;

    public GenericConstruction(Construction c) {
        this.name = "Construction";
        this.construction = c;
        this.width = c.getWidth();
        this.height = c.getHeight();
        this.constructionDuration = c.getConstructionDuration();
        this.tiles.forEach(construction::addTile);
        this.character = '%';
    }

    public GenericConstruction(Construction c, boolean b) {
        this.name = "Construction";
        this.construction = c;
        this.width = c.getWidth();
        this.height = c.getHeight();
        this.constructionDuration = c.getConstructionDuration();
        this.tiles.forEach(construction::addTile);
        this.character = '%';
        this.constructed = b;
    }


    public void Construct() {
        System.out.println(this.constructionProgress);
        if (this.constructionProgress < this.constructionDuration) {
            this.constructionProgress += constructionSpeed;
        } else {
            this.constructed = true;
        }

    }

    public void deConstruct() {
        if (this.constructionProgress < this.constructionDuration) {
            this.constructionProgress += constructionSpeed;
        } else {
            this.constructed = false;
            this.destroy();
        }
    }

    public Construction getConstruction() {
        return this.construction;
    }

}
