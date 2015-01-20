package com.fuzzy.autocity.generators.strings;

import com.fuzzy.autocity.world.buildings.prefabs.Store;

import java.util.Random;

public class StoreName {
    public static String getString(Store store) {
        return "Dragon Dildo Emporium " + (new Random().nextInt());
    }
}
