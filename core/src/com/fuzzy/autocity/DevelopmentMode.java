package com.fuzzy.autocity;

import com.fuzzy.autocity.debugui.Cursor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DevelopmentMode {

    private static final String deLimiter = " ";
    private Game game;
    private Cursor cursor;

    public DevelopmentMode(Game game, Cursor cursor) {
        this.game = game;
        this.cursor = cursor;
    }

    public void commandLookup(String command) { // This is still being worked on
        System.out.println(">" + command);
        String[] tmp = command.split(deLimiter);
        try {
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
                            if (invokable.getName().contains(tmp[1])) {
                                invokeCommand(invokable, this.game, tmp);
                                return;
                            }
                        }
                    }
            }
        } catch (Exception e) {
            System.out.println("Invalid command @" + e.toString());
        }
    }

    private void invokeCommand(Method invokable, Object invokingObject, String[] command) throws InvocationTargetException, IllegalAccessException {

        if (invokable.getReturnType().equals(Void.TYPE)) {
            if (invokable.getGenericParameterTypes() != null) {
                Object[] args = new Object[invokable.getGenericParameterTypes().length];
//TODO I have to manually define cases for each possible parameter type ;3;
            } else { // Void functions without params are simple, we just run them
                invokable.invoke(invokingObject);
            }
        } else {
            // These four return types can be displayed right away.
            if (invokable.getReturnType().equals(String.class)
                    || invokable.getReturnType().equals(Integer.class)
                    || invokable.getReturnType().equals(java.lang.Character.class)
                    || invokable.getReturnType().equals(Boolean.class)) {
                System.out.println(invokable.getName() + " returns:");
                System.out.println(invokable.invoke(invokingObject));
            } else { // It'll need to be setup differently to be human readable
                System.out.println("rrerr");
            }
        }
    }


}

