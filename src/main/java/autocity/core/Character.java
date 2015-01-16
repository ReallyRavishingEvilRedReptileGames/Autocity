package autocity.core;

import autocity.core.character.Sex;
import autocity.core.enumeration.ECharacterSex;
import autocity.core.generators.strings.GenericPersonName;
import autocity.core.world.WorldObject;

import java.util.ArrayList;

public abstract class Character implements Comparable<Character> {
    protected final int thoughtLimit = 10;
    protected int maxHealth = 100;
    protected ArrayList<Thought> thoughts;
    protected String name;
    protected WorldObject location;
    protected Sex sex = new Sex(ECharacterSex.Male);

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Character() {
        this.thoughts = new ArrayList<>();
        this.name = new GenericPersonName(this).getFullName();
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

    public int compareTo(Character character) {
        return this.name.compareTo(character.getName());
    }

    public WorldObject getLocation() {
        return this.location;
    }

    public void setLocation(WorldObject location) {
        if (this.location != null) {
            this.location.removeVisitor(this);
        }

        this.location = location;

        if (this.location != null) {
            this.location.addVisitor(this);
        } else {
            // TODO: how should we handle persons being nowhere?
        }
    }

    public String toString() {
        return this.name;
    }
}
