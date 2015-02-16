package com.fuzzy.autocity.generators.strings;

import com.fuzzy.autocity.world.buildings.WorldObject;

import java.util.Random;

public class StoreName {
    public static String getString(WorldObject worldObject) {
        return "Dragon Dildo Emporium " + (new Random().nextInt());
    }
}
