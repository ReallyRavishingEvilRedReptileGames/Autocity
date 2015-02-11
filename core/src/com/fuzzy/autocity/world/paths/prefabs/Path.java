package com.fuzzy.autocity.world.paths.prefabs;

import com.fuzzy.autocity.world.buildings.Construction;

public class Path extends Construction {

    public Path() {

    }

    public Path(Path p) {
        this.name = p.getName();
        this.character = p.getCharacter();
        this.constructionDuration = p.getConstructionDuration();
    }
}
