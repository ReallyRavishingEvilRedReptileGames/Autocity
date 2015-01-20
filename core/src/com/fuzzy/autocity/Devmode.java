package com.fuzzy.autocity;

import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;

/**
 * Created by Whiplash on 1/20/2015.
 */
public class Devmode {


    private static final String deLimiter = "\\.";
    private Game game;

    public Devmode(Game game) {
        this.game = game;
    }

    public void commandLookup(String command) {
        System.out.println("> " + command);
        String[] tmp = command.split(deLimiter);
        switch (tmp[0].toLowerCase()) {
            case "tile":
                try {
                    game.getWorld().getTile(Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2])).Execute(command);
                } catch (TileOutOfBoundsException | IndexOutOfBoundsException e) {
                    System.out.println("Invalid tile position.");
                }
                return;
            default:
                System.out.println("Invalid command.");
                System.out.println(" @" + Devmode.class.getName());
        }
    }
}
