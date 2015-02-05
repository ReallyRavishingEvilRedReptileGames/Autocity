package com.fuzzy.autocity.debugui;

import com.fuzzy.autocity.*;
import com.fuzzy.autocity.exceptions.TerrainConflictException;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.exceptions.WorldObjectConflictException;
import com.fuzzy.autocity.factories.WorldObjectFactory;
import com.fuzzy.autocity.world.WorldObject;
import com.fuzzy.autocity.world.buildings.prefabs.Construction;

import java.awt.event.KeyEvent;
import java.lang.Character;
import java.util.HashMap;
import java.util.Map;

public class Cursor implements Invokable {
    private int x = 0, y = 0;
    private int width = 1, height = 1;
    private boolean buildingSelected = false;
    private Game game;
    WorldObject o = null;
    private char character = '#';
    private Map<Character, String> keyCodes;

    //TODO: Make it so the cursor can have a displayed width and height like buildings,
//TODO: so we can preview where the building is going to be placed before placing it.
    public Cursor(Game game) {
        this.game = game;
        keyCodes = new HashMap<>();
        keyCodes.put('r', "road");
        keyCodes.put('h', "hut");
        keyCodes.put('t', "pine tree");
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
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
        return this.character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Tile getSelectedTile() {
        return this.game.getWorld().getTile(x, y);
    }

    public void Move(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && this.y > 0) {
            this.y--;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && this.y < this.game.getWorld().getHeight() - 1) {
            this.y++;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && this.x > 0) {
            this.x--;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && x < this.game.getWorld().getWidth() - 1) {
            this.x++;
        }
    }

    public void Place(KeyEvent e) {
        PlacementValidator p = new PlacementValidator(this.game.getWorld());
        Tile tile = getSelectedTile();
        WorldObjectFactory wof = new WorldObjectFactory();
        if (e.getKeyCode() == KeyEvent.VK_UP
                || e.getKeyCode() == KeyEvent.VK_DOWN
                || e.getKeyCode() == KeyEvent.VK_LEFT
                || e.getKeyCode() == KeyEvent.VK_RIGHT
                || e.getKeyChar() == 'p') {
            return;
        }
        try {
            this.o = wof.createWorldObject(keyCodes.get(e.getKeyChar()));
            p.validateWorldObject(o, this.x, this.y);
            if (o instanceof Construction) {
                this.game.getWorld().buildConstruction((Construction) o, tile);
                this.buildingSelected = false;
            } else {
                this.game.getWorld().placeWorldObject(o, tile);
            }
            this.o = null;
        } catch (TileOutOfBoundsException | WorldObjectConflictException | TerrainConflictException ignored) {

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
