package autocity.core.simulation;

import autocity.core.Settlement;

/**
 * This class is for handling the simulation of population growth/shrinkage based on reproduction, age, health and other factors that are yet to be implemented
 */
public class Population {
    private Settlement settlement;

    public Population(Settlement settlement) {
        this.settlement = settlement;
    }

    public void update() {
        System.out.println("Here we would be updating the population for " + this.settlement);
    }
}
