package autocity.core;

import java.util.ArrayList;

public abstract class Civilian {
    protected int maxHealth = 100;
    protected ArrayList<Thought> thoughts;
    protected String name;

    protected final int thoughtLimit = 10;

    public Civilian() {
        thoughts = new ArrayList<>();
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
}
