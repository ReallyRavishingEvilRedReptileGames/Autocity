package com.fuzzy.autocity.generators.strings;

import com.fuzzy.autocity.Character;

import java.util.ArrayList;
import java.util.Random;

public class GenericPersonName {
    static String[] maleFirstNamePool = {"James", "Samuel", "Michael", "Henry", "Richard"};
    static String[] femaleFirstNamePool = {"Anna", "Annabelle"};
    static String[] androgynousFirstNamePool = {"Jamie", "Sam"};

    static String[] lastNamePrefixPool = {"gold", "white", "blue", "grey", "wal", "pel", "ivy", "rose", "rover", "butter"};
    static String[] lastNameSuffixPool = {"worth", "stein", "berg", "neer", "wood", "hall", "birth", "birch", "lain", "sall", "dall", "ner", "vale", "rose", "field", "bottom"};

    private Random random;
    private Character character;

    public GenericPersonName(Character character) {
        this.random = new Random();
        this.character = character;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public String getFirstName() {
        ArrayList<String[]> pools = new ArrayList<>();

        switch (this.character.getSex().getE()) {
            case Male:
                pools.add(maleFirstNamePool);
                pools.add(androgynousFirstNamePool);
                break;
            case Female:
                pools.add(femaleFirstNamePool);
                pools.add(androgynousFirstNamePool);
                break;
            default:
                pools.add(maleFirstNamePool);
                pools.add(femaleFirstNamePool);
                pools.add(androgynousFirstNamePool);
                break;
        }

        String[] pool = pools.get(random.nextInt(pools.size()));

        return pool[random.nextInt(pool.length)];
    }

    public String getLastName() {
        String lastName = lastNamePrefixPool[random.nextInt(lastNamePrefixPool.length)] + lastNameSuffixPool[random.nextInt(lastNameSuffixPool.length)];

        try {
            lastName = String.valueOf(lastName.charAt(0)).toUpperCase() + lastName.substring(1);
        } catch (StringIndexOutOfBoundsException e) {
            // lastName empty
        }

        return lastName;
    }
}
