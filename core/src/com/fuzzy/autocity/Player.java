package com.fuzzy.autocity;

public class Player {
    private String name;
    private boolean hasCivilization;

    public Player() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasCivilization() {
        return hasCivilization;
    }

    public void setHasCivilization(boolean hasCivilization) {
        this.hasCivilization = hasCivilization;
    }
}