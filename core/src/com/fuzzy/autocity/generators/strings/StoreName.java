package com.fuzzy.autocity.generators.strings;

import com.fuzzy.autocity.world.buildings.prefabs.Industrial;

import java.util.Random;

public class StoreName {
    public static String getString(Industrial industrial) {
        return "Dragon Dildo Emporium " + (new Random().nextInt());
    }
}
