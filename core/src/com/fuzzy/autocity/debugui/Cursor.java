package com.fuzzy.autocity.debugui;

import com.fuzzy.autocity.*;
import com.fuzzy.autocity.exceptions.TerrainConflictException;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.exceptions.WorldObjectConflictException;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.buildings.Hut;
import com.fuzzy.autocity.world.buildings.prefabs.Building;
import com.fuzzy.autocity.world.Construction;
import com.fuzzy.autocity.world.paths.Road;
import com.fuzzy.autocity.world.resources.PineTree;
import com.fuzzy.autocity.world.resources.prefabs.Tree;

import java.awt.event.KeyEvent;

public class Cursor implements Invokable {
    private int x = 0, y = 0;
    private int width = 1, height = 1;
    private boolean buildingSelected = false;
    private Game game;
    WorldObject o = null;
    private char character = '#';

    public Cursor(Game game) {
        this.game = game;
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

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Tile getSelectedTile() {
        try {
            return this.game.getWorld().getTile(x, y);
        } catch (TileOutOfBoundsException e) {
            return null;
        }
    }

    public void Move(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && y > 0) {
            y--;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && y < this.game.getWorld().getHeight() - 1) {
            y++;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && x > 0) {
            x--;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && x < this.game.getWorld().getWidth() - 1) {
            x++;
        }
    }

    //TODO: Some sort of world object list to iterate over and compare characters for ez placement?
    //TODO: Made this ^^^ now I just need to implement it here.
    public void Place(KeyEvent e) {
        PlacementValidator p = new PlacementValidator(this.game.getWorld());
        Tile tile = getSelectedTile();

        if (e.getKeyChar() == 'r') {
            Road r = new Road();
            try {
                p.validateWorldObject(r, x, y);
                this.game.getWorld().buildConstruction(r, tile);
            } catch (TileOutOfBoundsException | WorldObjectConflictException | TerrainConflictException e1) {

            }

        } else if (e.getKeyChar() == 'h') {
            if (!buildingSelected) {
                o = new Hut();
                this.width = o.getWidth();
                this.height = o.getHeight();
                buildingSelected = true;
            } else {
                try {
                    p.validateBuilding((Building) o, x, y);
                    this.game.getWorld().buildConstruction((Construction) o, tile);
                } catch (TileOutOfBoundsException | WorldObjectConflictException | TerrainConflictException e2) {

                }
            }
        } else if (e.getKeyChar() == 't') {
            Tree t = new PineTree();
            try {
                p.validateWorldObject(t, x, y);
                this.game.getWorld().placeWorldObject(t, tile);
            } catch (TileOutOfBoundsException | WorldObjectConflictException | TerrainConflictException e3) {

            }
        }
    }

    public void deConstruct() {
        WorldObject wo = getSelectedTile().getOccupyingObject();
        if (wo instanceof Construction) {
            this.game.getWorld().removeConstruction((Construction) wo);
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
                    System.out.println(" @" + this.getClass().getName());
                }
                return;
            default:
                System.out.println("Invalid command.");
                System.out.println(" @" + this.getClass().getName());
        }
    }
}
