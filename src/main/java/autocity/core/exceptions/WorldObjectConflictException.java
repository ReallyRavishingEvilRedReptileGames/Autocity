package autocity.core.exceptions;

import autocity.core.tiles.WorldObject;

public class WorldObjectConflictException extends Exception {
    private WorldObject worldObject;

    public WorldObjectConflictException(WorldObject worldObject) {
        this.worldObject = worldObject;
    }

    public WorldObject getWorldObject() {
        return this.worldObject;
    }
}
