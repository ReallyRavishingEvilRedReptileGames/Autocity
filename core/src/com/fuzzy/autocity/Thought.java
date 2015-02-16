package com.fuzzy.autocity;

import com.fuzzy.autocity.enumeration.EThoughtType;
import com.fuzzy.autocity.world.buildings.WorldObject;

public class Thought {
    private EThoughtType type;
    private WorldObject worldObject;
    private Character character;

    public Thought(EThoughtType type, WorldObject worldObject, Character character) {
        this.type = type;
        this.worldObject = worldObject;
        this.character = character;
    }

    public Thought(EThoughtType type, WorldObject worldObject) {
        this.type = type;
        this.worldObject = worldObject;
    }

    public Thought(EThoughtType type, Character character) {
        this.type = type;
        this.character = character;
    }

    public Thought(EThoughtType type) {
        this.type = type;
    }

    public String toString() {
        return String.format(this.getFormatString(this.type), this.worldObject, this.character);
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
