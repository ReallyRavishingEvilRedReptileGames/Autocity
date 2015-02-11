package com.fuzzy.autocity.generators.strings;

import com.fuzzy.autocity.world.buildings.Building;

import java.util.Random;

public class StoreName {
    public static String getString(Building building) {
        return "Dragon Dildo Emporium " + (new Random().nextInt());
    }
}
