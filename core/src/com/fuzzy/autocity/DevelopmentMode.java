package com.fuzzy.autocity;

import com.fuzzy.autocity.debugui.Cursor;
import com.fuzzy.autocity.factories.WorldObjectFactory;
import com.fuzzy.autocity.generators.builders.PathBuilder;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.buildings.Construction;

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
            case "Object":
                commandLookup(command, this.game.getWorld().getTile(this.cursor.getX(), this.cursor.getY()).getOccupyingObject());
                return;
            case "Pathbuilder":
                commandLookup(command, new PathBuilder(this.game.getWorld()));
                return;
            case "World":
                commandLookup(command, this.game.getWorld());
                return;
            case "Help":
                System.out.println("Usage: \"[module] Help\" \n Example: \"Game Help\"");
                System.out.println("Valid modules:\n Tile \n Game \n Object \n Pathbuilder \n World");

        }
    }

    private void commandLookup(String command, Object invokingObject) {
        String[] tmp = command.split(deLimiter);
        try {
            for (Method invokable : invokingObject.getClass().getMethods()) {
                if (invokable.isAnnotationPresent(Invokable.class)) {
                    if (tmp[1].contains("Help")) {
                        System.out.println(invokable.getName() + Arrays.toString(invokable.getParameterTypes()) + "\n returns type: " + invokable.getReturnType());
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
                for (int x = 0; x < invokable.getParameterCount(); x++) {
                    if (invokable.getParameterTypes()[x].equals(Boolean.TYPE)) {
                        args[x] = command[x + 2].contains("True");
                    } else if (invokable.getParameterTypes()[x].equals(Integer.TYPE)) {
                        args[x] = Integer.valueOf(command[x + 2]);
                    } else if (invokable.getParameterTypes()[x].equals(Tile.class)) {
                        String[] coords = command[x + 2].split(",");
                        args[x] = this.game.getWorld().getTile(Integer.valueOf(coords[0]), Integer.valueOf(coords[1]));
                    } else if (invokable.getParameterTypes()[x].equals(Construction.class)) {
                        // Can't create any world objects that have spaces in their name right now.
                        args[x] = new WorldObjectFactory().createWorldObject(command[x + 2]);
                    } else if (invokable.getParameterTypes()[x].equals(WorldObject.class)) {
                        args[x] = new WorldObjectFactory().createWorldObject(command[x + 2]);
                    } else {
                        System.out.println(invokable.getParameterTypes()[x].toString());
                        System.out.println("Can't let you do that STARFOX!");
                        return; // TODO: Rest of the cases for game specific objects.
                    }
                }
                System.out.println(Arrays.toString(invokable.getParameterTypes()));
                System.out.println(Arrays.toString(args));
                invokable.invoke(invokingObject, args);
            } else { // Void functions without params are simple, we just run them
                invokable.invoke(invokingObject);
            }
        } else { // Just print out the returned info.
            System.out.println(invokable.getName() + " " + invokable.invoke(invokingObject));
        }
    }
}

