package com.fuzzy.autocity;

import com.fuzzy.autocity.character.Sex;
import com.fuzzy.autocity.enumeration.ECharacterSex;
import com.fuzzy.autocity.generators.strings.GenericPersonName;
import com.fuzzy.autocity.world.WorldObject;

import java.util.ArrayList;

public abstract class Character implements Comparable<Character> {
    protected final int thoughtLimit = 10;
    protected int maxHealth = 100;
    protected ArrayList<Thought> thoughts;
    protected String name;
    protected WorldObject location;
    protected Sex sex = new Sex(ECharacterSex.Male);

    public Character() {
        this.thoughts = new ArrayList<>();
        this.name = new GenericPersonName(this).getFullName();
    }

    @Invokable
    public Sex getSex() {
        return sex;
    }

    @Invokable
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Invokable
    public String getName() {
        return name;
    }

    @Invokable
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
            /* Until we figure out what scenarios would cause them to be "nowhere"
            we should remove them from the game/kill them off/dispose of them.
             */
        }
    }

    public String toString() {
        return this.name;
    }
}
