package com.fuzzy.autocity.world.resources.prefabs;

import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.resources.Resource;

public class Tree extends Resource {

    public Tree() {
        
    }

    public Tree(Tree t) {
        this.name = t.getName();
        this.character = t.getCharacter();
    }
}
