package autocity.core;

import autocity.core.generators.PersonName;
import autocity.core.tiles.WorldObject;
import autocity.core.tiles.buildings.prefabs.Building;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Person implements Comparable<Person> {
    protected int maxHealth = 100;
    protected ArrayList<Thought> thoughts;
    protected String name;
    protected Settlement settlement;
    protected WorldObject location;

    protected final int thoughtLimit = 10;

    public Person() {
        this.thoughts = new ArrayList<>();
        this.name = PersonName.getString(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addThought(Thought thought) {
        thoughts.add(thought);

        // trim earlier thoughts if we're at the limit.
        if (thoughts.size() > thoughtLimit) {
            thoughts.remove(0);
        }
    }

    public ArrayList<Thought> getThoughts() {
        return thoughts;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public int compareTo(Person person) {
        return this.name.compareTo(person.getName());
    }

    public void setLocation(WorldObject location) {
        if (this.location != null) {
            this.location.removeVisitor(this);
        }

        this.location = location;

        if (this.location != null) {
            this.location.addVisitor(this);
        } else {
            // TODO: how do we handle persons being nowhere?
        }
    }

    public WorldObject getLocation() {
        return this.location;
    }

    public String toString() {
        return this.name;
    }
}
