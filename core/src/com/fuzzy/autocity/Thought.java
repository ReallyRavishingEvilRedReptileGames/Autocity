package com.fuzzy.autocity;

import com.fuzzy.autocity.enumeration.EThoughtType;
import com.fuzzy.autocity.world.buildings.prefabs.Building;

public class Thought {
    private EThoughtType type;
    private Building building;
    private Character character;

    public Thought(EThoughtType type, Building building, Character character) {
        this.type = type;
        this.building = building;
        this.character = character;
    }

    public Thought(EThoughtType type, Building building) {
        this.type = type;
        this.building = building;
    }

    public Thought(EThoughtType type, Character character) {
        this.type = type;
        this.character = character;
    }

    public Thought(EThoughtType type) {
        this.type = type;
    }

    public String toString() {
        return String.format(this.getFormatString(this.type), this.building, this.character);
    }

    public String getFormatString(EThoughtType type) {
        // todo move this out to an external locale file or something, I dunno.
        // a switch isn't best for this in the long run. This is just temporary.
        switch (type) {
            default:
                return "%1s %2s";
            case ConstructingBuilding:
                return "I'm going to build that %1s.";
            case PrivilegeChecked:
                return "I've checked my privilege today.";
        }
    }
}
