package com.fuzzy.autocity.debugui;

import com.fuzzy.autocity.*;
import com.fuzzy.autocity.exceptions.TerrainConflictException;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.exceptions.WorldObjectConflictException;
import com.fuzzy.autocity.world.buildings.Hut;
import com.fuzzy.autocity.world.paths.Road;
import com.fuzzy.autocity.world.resources.PineTree;
import com.fuzzy.autocity.world.resources.prefabs.Tree;

import java.awt.event.KeyEvent;

public class Cursor implements Invokable {
    private int x = 0, y = 0;
    private World world;
    private char character = '#';

    public Cursor(World world) {
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Tile getSelectedTile() {
        try {
            return this.world.getTile(x, y);
        } catch (TileOutOfBoundsException e) {
            return null;
        }
    }

    public void Move(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && y > 0) {
            y--;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && y < world.getHeight() - 1) {
            y++;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && x > 0) {
            x--;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && x < world.getWidth() - 1) {
            x++;
        }
    }

    //TODO: Some sort of world object list to iterate over and compare characters for ez placement?
    public void Place(KeyEvent e) {
        PlacementValidator p = new PlacementValidator(this.world);
        if (e.getKeyChar() == 'r') {
            try {
                Road r = new Road();
                p.validateWorldObject(r, x, y);
                getSelectedTile().setOccupyingObject(r);
            } catch (TileOutOfBoundsException | WorldObjectConflictException e1) {

            }
        } else if (e.getKeyChar() == 'h') {
            try {
                Hut h = new Hut();
                p.validateBuilding(h, x, y);
                getSelectedTile().setOccupyingObject(h);
            } catch (TileOutOfBoundsException | WorldObjectConflictException | TerrainConflictException e2) {

            }
        } else if (e.getKeyChar() == 't') {
            try {
                Tree t = new PineTree();
                p.validateWorldObject(t, x, y);
                getSelectedTile().setOccupyingObject(t);
            } catch (TileOutOfBoundsException | WorldObjectConflictException e3) {

            }
        }
    }

    @Override
    public void Execute(String command) {
        String[] tmp = command.split(delimiter);
        switch (tmp[1]) {
            case "help":
                System.out.println("GetCoords, SetCoords");
                return;
            case "getcoords":
                System.out.println("X: " + getX() + " Y: " + getY());
                return;
            case "setcoords":
                try {
                    setCoords(Integer.valueOf(tmp[2]), Integer.valueOf(tmp[3]));
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid command.");
                    System.out.println(" @" + Devmode.class.getName());
                }
                return;
            default:
                System.out.println("Invalid command.");
                System.out.println(" @" + Devmode.class.getName());
        }
    }
}
