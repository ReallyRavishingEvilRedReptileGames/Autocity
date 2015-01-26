package com.fuzzy.autocity;

import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.world.buildings.prefabs.Constructable;

import java.util.HashSet;

public class World {
    private int width;
    private int height;
    private Tile[][] tiles;
    private HashSet<Settlement> settlements;
    private HashSet<Constructable> constructions;
    private HashSet<Constructable> deconstructions;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.settlements = new HashSet<>();
        this.constructions = new HashSet<>();
        this.deconstructions = new HashSet<>();

        this.addTiles();
    }

    private void addTiles() {
        this.tiles = new Tile[this.width][this.height];

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.tiles[i][j] = new Tile(i, j);
            }
        }
    }

    public void setTile(int x, int y, Tile tile) {
        tile.setHeight(tiles[x][y].getHeight());
        this.tiles[x][y] = tile;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile getTile(int x, int y) throws TileOutOfBoundsException {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new TileOutOfBoundsException();
        }

        return this.tiles[x][y];
    }

    public void addSettlement(Settlement settlement) {
        this.settlements.add(settlement);
    }

    public HashSet<Settlement> getSettlements() {
        return this.settlements;
    }

    public void addToConstructionList(Constructable c) {
        constructions.add(c);
    }

    public HashSet<Constructable> getConstructions() {
        return this.constructions;
    }

    public void addToDeConstructionList(Constructable c) {
        deconstructions.add(c);
    }

    public HashSet<Constructable> getDeconstructions() {
        return this.deconstructions;
    }


    public void forEachTile(Object object) {

    }
}
