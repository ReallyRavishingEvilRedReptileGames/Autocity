package com.fuzzy.autocity.world.buildings.prefabs;

import com.fuzzy.autocity.Character;

import java.util.HashSet;

public class Residential extends Building {
    protected HashSet<Character> residents = new HashSet<>();
    protected int baseCapacity = 4;

    public Residential() {
//        this.name = "Residential";
    }

    public int getBaseCapacity() {
        return baseCapacity;
    }

    public void setBaseCapacity(int baseCapacity) {
        this.baseCapacity = baseCapacity;
    }

    public HashSet<Character> getResidents() {
        return this.residents;
    }

    public void addResident(Character character) {
        this.residents.add(character);
    }

    public void removeResident(Character character) {
        this.residents.remove(character);
    }
}
