package autocity.core.tiles.buildings.prefabs;

import autocity.core.Person;

import java.util.HashSet;

public abstract class Residential extends Building {
    protected HashSet<Person> residents;

    public Residential() {
        this.residents = new HashSet<>();
    }

    public HashSet<Person> getResidents() {
        return this.residents;
    }

    public void addResident(Person person) {
        this.residents.add(person);
    }

    public void removeResident(Person person) {
        this.residents.remove(person);
    }
}
