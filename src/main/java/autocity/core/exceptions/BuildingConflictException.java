package autocity.core.exceptions;

import autocity.core.world.buildings.prefabs.Building;

public class BuildingConflictException extends Exception {
    private Building building;

    public BuildingConflictException(Building building) {
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }
}
