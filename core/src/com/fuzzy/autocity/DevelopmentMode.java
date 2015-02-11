package com.fuzzy.autocity;

import com.fuzzy.autocity.debugui.Cursor;
import com.fuzzy.autocity.generators.builders.PathBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DevelopmentMode {

    private static final String deLimiter = " ";
    private Game game;
    private Cursor cursor;

    public DevelopmentMode(Game game, Cursor cursor) {
        this.game = game;
        this.cursor = cursor;
    }

    public void commandLookup(String command) { // This is still being worked
        System.out.println(">" + command);
        String[] tmp = command.split(deLimiter);
        switch (tmp[0]) {
            case "tile":
                for (Method invokable : Tile.class.getDeclaredMethods()) {
                    if (invokable.isAnnotationPresent(Invokable.class)) {
                        System.out.println(invokable.getName());
                    }
                }
                return;
            case "game":
                for (Method invokable : this.game.getClass().getDeclaredMethods()) {
                    if (invokable.isAnnotationPresent(Invokable.class)) {
                        System.out.println(invokable.getName());
                    }
                }
                return;
        }
    }
}
