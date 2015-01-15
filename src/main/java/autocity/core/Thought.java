package autocity.core;

import autocity.core.tiles.buildings.prefabs.Building;
import autocity.enums.EThoughtType;

public class Thought {
    private EThoughtType type;
    private Building building;
    private Person person;

    public Thought(EThoughtType type, Building building, Person person) {
        this.type = type;
        this.building = building;
        this.person = person;
    }

    public Thought(EThoughtType type, Building building) {
        this.type = type;
        this.building = building;
    }

    public Thought(EThoughtType type, Person person) {
        this.type = type;
        this.person = person;
    }

    public Thought(EThoughtType type) {
        this.type = type;
    }

    public String toString() {
        return String.format(this.getFormatString(this.type), this.building, this.person);
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
