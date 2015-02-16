package com.fuzzy.autocity.exceptions;

import com.fuzzy.autocity.world.buildings.WorldObject;

public class BuildingConflictException extends Exception {
    private WorldObject worldObject;

    public BuildingConflictException(WorldObject worldObject) {
        this.worldObject = worldObject;
    }

    public WorldObject getWorldObject() {
        return worldObject;
    }
}
