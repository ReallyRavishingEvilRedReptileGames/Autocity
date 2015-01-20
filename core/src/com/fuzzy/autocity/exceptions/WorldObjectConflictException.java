package com.fuzzy.autocity.exceptions;

import com.fuzzy.autocity.world.WorldObject;

public class WorldObjectConflictException extends Exception {
    private WorldObject worldObject;

    public WorldObjectConflictException(WorldObject worldObject) {
        this.worldObject = worldObject;
    }

    public WorldObject getWorldObject() {
        return this.worldObject;
    }
}
