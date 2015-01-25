package com.fuzzy.autocity.debugui;

import com.fuzzy.autocity.Devmode;
import com.fuzzy.autocity.Invokable;
import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.World;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;

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
