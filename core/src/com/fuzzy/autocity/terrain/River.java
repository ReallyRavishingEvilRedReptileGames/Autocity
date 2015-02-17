package com.fuzzy.autocity.terrain;

import com.fuzzy.autocity.Terrain;
import com.fuzzy.autocity.enumeration.EDirection;

public class River extends Water {

    private EDirection flowDirection;

    public River() {
        this.name = "River";
        this.character = '\u2191';
        this.randomEntitySpawnRate = -1;
    }

    public void setFlowDirection(EDirection flowDirection) {
        this.flowDirection = flowDirection;
    }

    public EDirection getFlowDirection() {
        return flowDirection;
    }

    @Override
    public String getRandomTerrainObject() {
        return null;
    }

    @Override
    public char getCharacter() {
        if (flowDirection == null) {
            return '\u2195';
        }
        switch (flowDirection) {
            case North:
                return '\u2191';
            case South:
                return '\u2193';
            case East:
                return '\u2192';
            case West:
                return '\u2190';
            default:
                return '\u2195';
        }
    }
}
