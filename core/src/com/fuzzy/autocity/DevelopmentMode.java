package com.fuzzy.autocity;

import com.fuzzy.autocity.debugui.Cursor;

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

    public void Execute(String command) {
        System.out.println(">" + command);
        String[] tmp = command.split(deLimiter);

        switch (tmp[0]) {
            case "Tile":
                commandLookup(command, this.game.getWorld().getTile(this.cursor.getX(), this.cursor.getY()));
                return;
            case "Game":
                commandLookup(command, this.game);
                return;
            case "Help":
                System.out.println("Usage: \"[module] Help\" \n Example: \"Game Help\"");

        }
    }

    private void commandLookup(String command, Object invokingObject) {
        String[] tmp = command.split(deLimiter);
        try {
            for (Method invokable : invokingObject.getClass().getDeclaredMethods()) {
                if (invokable.isAnnotationPresent(Invokable.class)) {
                    if (tmp[1].contains("Help")) {
                        System.out.println(invokable.getName() + Arrays.toString(invokable.getParameterTypes()) + " returns type: " + invokable.getReturnType());
                    } else if (invokable.getName().contains(tmp[1])) {
                        invokeCommand(invokable, invokingObject, tmp);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid command @" + e.toString());
        }
    }

    private void invokeCommand(Method invokable, Object invokingObject, String[] command) throws InvocationTargetException, IllegalAccessException {

        if (invokable.getReturnType().equals(Void.TYPE)) {
            if (invokable.getParameterCount() != 0) {
                Object[] args = new Object[invokable.getParameterCount()];
//TODO I have to manually define cases for each possible parameter type ;3;
                for (int x = 0; x < invokable.getParameterCount(); x++) {
                    if (invokable.getParameterTypes()[x].equals(Boolean.TYPE)) {
                        args[x] = command[x + 2].contains("True");
                    } else if (invokable.getParameterTypes()[x].equals(Integer.TYPE)) {
                        args[x] = Integer.valueOf(command[x + 2]);
                    }
                }
                System.out.println(Arrays.toString(invokable.getParameterTypes()));
                System.out.println(Arrays.toString(args));
                invokable.invoke(invokingObject, args);
            } else { // Void functions without params are simple, we just run them
                invokable.invoke(invokingObject);
            }
        } else {

            System.out.println(invokable.getName() + " " + invokable.invoke(invokingObject));

        }
    }


}

