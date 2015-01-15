package autocity.core.generators;

import autocity.core.Person;

import java.util.Random;

public class PersonName {
    public static String getString(Person person) {
        return "Gabe Newell " + (new Random().nextInt());
    }
}
