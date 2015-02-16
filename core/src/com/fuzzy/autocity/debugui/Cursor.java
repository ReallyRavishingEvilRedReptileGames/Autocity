package com.fuzzy.autocity.debugui;

import com.fuzzy.autocity.*;
import com.fuzzy.autocity.exceptions.TerrainConflictException;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.exceptions.WorldObjectConflictException;
import com.fuzzy.autocity.factories.WorldObjectFactory;
import com.fuzzy.autocity.world.buildings.WorldObject;
import com.fuzzy.autocity.world.buildings.Construction;

import java.awt.event.KeyEvent;
import java.lang.Character;
import java.util.HashMap;
import java.util.Map;


public class Cursor {
    private int x = 0, y = 0;
    private int width = 1, height = 1;
    private boolean buildingSelected = false;
    private Game game;
    com.fuzzy.autocity.world.WorldObject o = null;
    private char character = '#';
    private Map<Character, String> keyCodes;
    private Tile[] tiles;

    //TODO: Make it so the cursor can have a displayed width and height like buildings,
//TODO: so we can preview where the building is going to be placed before placing it.
    public Cursor(Game game) {
        this.game = game;
        this.tiles = new Tile[2];
        keyCodes = new HashMap<>();
        keyCodes.put('r', "road");
        keyCodes.put('h', "Hut");
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

    public Tile getTileAtCursor() {
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

    public void selectTile() {
        if (tiles[0] == null) {
            tiles[0] = this.game.getWorld().getTile(this.x, this.y);
        } else {
            tiles[1] = this.game.getWorld().getTile(this.x, this.y);
        }
        if (tiles[0] != null && tiles[1] != null) {
            int width = (tiles[1].getX() - tiles[0].getX()) + 1;
            int height = (tiles[1].getY() - tiles[0].getY()) + 1;
            System.out.println("Dimensions: \n X:" + width + "\n Y:" + height);
            PlacementValidator p = new PlacementValidator(this.game.getWorld());
            this.o = new WorldObject(width, height);
            try {
                p.validateWorldObject(o, tiles[0].getX(), tiles[0].getY());
                this.game.getWorld().placeWorldObject(o, tiles[0]);
            } catch (WorldObjectConflictException | TileOutOfBoundsException | TerrainConflictException ignored) {
            }
            clearSelectedTiles();
        }
    }

    public void clearSelectedTiles() {
        tiles[0] = null;
        tiles[1] = null;
    }

    public void Place(KeyEvent e) {
        PlacementValidator p = new PlacementValidator(this.game.getWorld());
        Tile tile = getTileAtCursor();
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
        } catch (TileOutOfBoundsException | WorldObjectConflictException | TerrainConflictException | NullPointerException ignored) {

        }
    }

    public void deConstruct() {
        com.fuzzy.autocity.world.WorldObject wo = getTileAtCursor().getOccupyingObject();
        if (wo instanceof Construction) {
            this.game.getWorld().removeConstruction((Construction) wo);
        }
    }

}
