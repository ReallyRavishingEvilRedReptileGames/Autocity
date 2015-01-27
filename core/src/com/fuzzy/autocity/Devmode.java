package com.fuzzy.autocity;

import com.fuzzy.autocity.debugui.Cursor;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.generators.builders.PathBuilder;

/**
 * Created by Whiplash on 1/20/2015.
 */
public class Devmode {


    private static final String deLimiter = "\\.";
    private Game game;
    private Cursor cursor;

    public Devmode(Game game, Cursor cursor) {
        this.game = game;
        this.cursor = cursor;
    }

    public void commandLookup(String command) {
        System.out.println("> " + command);
        String[] tmp = command.split(deLimiter);
        switch (tmp[0].toLowerCase()) {
            case "help": {
                System.out.println("Tile, Cursor, Game, World, Pathbuilder");
                return;
            }
            case "tile":
                try {
                    game.getWorld().getTile(cursor.getX(), cursor.getY()).Execute(command);
                } catch (TileOutOfBoundsException | IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Invalid tile position.");
                }
                return;
            case "cursor":
                cursor.Execute(command);
                return;
            case "game":
                game.Execute(command);
                return;
            case "pathbuilder":
                new PathBuilder(this.game.getWorld()).Execute(command);
                return;
            default:
                System.out.println("Invalid command.");
                System.out.println(" @" + Devmode.class.getName());
        }
    }
}
