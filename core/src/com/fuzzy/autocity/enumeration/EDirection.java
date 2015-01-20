package com.fuzzy.autocity.enumeration;

public enum EDirection {
    North, East, South, West;

    public EDirection getClockwiseRotation() {
        int newOrdinal = this.ordinal() + 1;

        if (newOrdinal > 3) {
            newOrdinal = 0;
        }

        return EDirection.values()[newOrdinal];
    }
}
