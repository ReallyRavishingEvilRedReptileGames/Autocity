package com.fuzzy.autocity;

import com.fuzzy.autocity.debugui.Cursor;
import com.fuzzy.autocity.generators.builders.PathBuilder;

public class Devmode {


    private static final String deLimiter = "\\.";
    private Game game;
    private Cursor cursor;
//    private static ArrayList<WorldObject> worldObjectArrayList;

    public Devmode(Game game, Cursor cursor) {
        this.game = game;
        this.cursor = cursor;
        populateList();
    }

    private void populateList() {
//        worldObjectArrayList = new ArrayList<>();
//        worldObjectArrayList.add(new PineTree());
//        worldObjectArrayList.add(new PalmTree());
//        worldObjectArrayList.add(new Road());
//        worldObjectArrayList.add(new TownHall());
//        worldObjectArrayList.add(new Smithy());
//        worldObjectArrayList.add(new Hut());
//        worldObjectArrayList.add(new Alchemist());
    }

//    public static WorldObject returnNewWorldObject(String s) {
//        try {
//            for (WorldObject w : worldObjectArrayList) {
//                if (w.getName().equalsIgnoreCase(s)) {
//                    return w.getClass().newInstance();
//                } else if (w.getCharacter() == s.charAt(0)) {
//                    return w.getClass().newInstance();
//                }
//            }
//        } catch (InstantiationException |IllegalAccessException | IndexOutOfBoundsException ignored) {
//        }
//        return null;
//    }

    public void commandLookup(String command) {
        System.out.println("> " + command);
        String[] tmp = command.split(deLimiter);
        switch (tmp[0].toLowerCase()) {
            case "help":
                System.out.println("Tile, Cursor, Game, Pathbuilder");
                return;
            case "tile":
                    this.game.getWorld().getTile(this.cursor.getX(), this.cursor.getY()).Execute(command);
                return;
            case "cursor":
                this.cursor.Execute(command);
                return;
            case "game":
                this.game.Execute(command);
                return;
            case "pathbuilder":
                new PathBuilder(this.game.getWorld()).Execute(command);
                return;
            default:
                System.out.println("Invalid command.");
                System.out.println(" @" + this.getClass().getName());
        }
    }
}
