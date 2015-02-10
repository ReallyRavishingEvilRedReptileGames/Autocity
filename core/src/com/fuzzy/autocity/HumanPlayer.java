package com.fuzzy.autocity;

public class HumanPlayer extends Player {

    public HumanPlayer() {
        this.setName(System.getProperty("user.name"));
    }

}
