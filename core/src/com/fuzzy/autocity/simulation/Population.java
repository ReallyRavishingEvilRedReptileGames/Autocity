package com.fuzzy.autocity.simulation;

import com.fuzzy.autocity.Settlement;

import java.util.Random;

/**
 * This class is for handling the simulation of population growth/shrinkage based on reproduction, age, health and other factors that are yet to be implemented
 */
public class Population {
    private Settlement settlement;
    private Random random;

    public Population(Settlement settlement) {
        this.settlement = settlement;
        this.random = new Random();
    }

    public void update() {
        // System.out.println("Updating population for " + this.settlement + "...");
    }
}
