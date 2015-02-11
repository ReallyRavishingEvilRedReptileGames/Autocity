package com.fuzzy.autocity.exceptions;

import com.fuzzy.autocity.world.buildings.Building;

public class BuildingConflictException extends Exception {
    private Building building;

    public BuildingConflictException(Building building) {
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }
}
