package autocity.core.generators;

import autocity.core.world.buildings.prefabs.Store;

import java.util.Random;

public class StoreName {
    public static String getString(Store store) {
        return "Dragon Dildo Emporium " + (new Random().nextInt());
    }
}
