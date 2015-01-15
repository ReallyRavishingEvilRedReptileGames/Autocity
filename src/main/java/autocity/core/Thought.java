package autocity.core;

import autocity.core.tiles.buildings.prefabs.Building;
import autocity.enums.EThoughtType;

public class Thought {
    private EThoughtType type;
    private Building building;
    private Civilian civilian;

    public Thought(EThoughtType type, Building building, Civilian civilian) {
        this.type = type;
        this.building = building;
        this.civilian = civilian;
    }

    public Thought(EThoughtType type, Building building) {
        this.type = type;
        this.building = building;
    }

    public Thought(EThoughtType type, Civilian civilian) {
        this.type = type;
        this.civilian = civilian;
    }

    public Thought(EThoughtType type) {
        this.type = type;
    }

    public String toString() {
        return String.format(this.getFormatString(this.type), this.building, this.civilian);
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
