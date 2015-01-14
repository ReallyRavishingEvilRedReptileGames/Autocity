package autocity.core;

import java.util.ArrayList;
import autocity.exceptions.OutOfBoundsException;

public class Map {
    private int width;
    private int height;
    private Tile[][] tiles;
    private ArrayList<Settlement> settlements;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.settlements = new ArrayList<>();

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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile getTile(int x, int y) throws OutOfBoundsException {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new OutOfBoundsException();
        }

        return this.tiles[x][y];
    }

    public void addSettlement(Settlement settlement) {
        this.settlements.add(settlement);
    }

    public ArrayList<Settlement> getSettlements() {
        return this.settlements;
    }
}
