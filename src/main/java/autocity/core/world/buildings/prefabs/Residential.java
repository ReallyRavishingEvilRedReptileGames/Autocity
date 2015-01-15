package autocity.core.world.buildings.prefabs;

import autocity.core.Character;

import java.util.HashSet;

public abstract class Residential extends Building {
    protected HashSet<Character> residents;

    public Residential() {
        this.residents = new HashSet<>();
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
