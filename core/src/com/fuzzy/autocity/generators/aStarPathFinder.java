package com.fuzzy.autocity.generators;

import com.fuzzy.autocity.Tile;
import com.fuzzy.autocity.World;
import com.fuzzy.autocity.exceptions.TileOutOfBoundsException;
import com.fuzzy.autocity.terrain.Grass;
import com.fuzzy.autocity.terrain.Water;

import java.util.ArrayList;
import java.util.Collections;

public class aStarPathFinder {

    private class Node implements Comparable {
        private int x;
        private int y;
        private float cost;
        private Node parent;
        private float heuristic;
        private int depth;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int setParent(Node parent) {
            depth = parent.depth + 1;
            this.parent = parent;
            return depth;
        }

        public int compareTo(Object other) {
            Node o = (Node) other;
            float f = heuristic + cost;
            float of = o.heuristic + o.cost;
            if (f < of) {
                return -1;
            } else if (f > of) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private class SortedList {
        private ArrayList list = new ArrayList();

        public Object first() {
            return list.get(0);
        }

        public void clear() {
            list.clear();
        }

        public void add(Object o) {
            list.add(o);
            Collections.sort(list);
        }

        public void remove(Object o) {
            list.remove(o);
        }

        public int size() {
            return list.size();
        }

        public boolean contains(Object o) {
            return list.contains(o);
        }
    }

    private ArrayList closed = new ArrayList();
    private SortedList open = new SortedList();
    private int maxSearchDistance;
    private World world;
    private Node[][] nodes;
    private Boolean[][] visitedTiles;

    public aStarPathFinder(World world, int maxSearchDistance) {
        this.world = world;
        this.maxSearchDistance = maxSearchDistance;
        visitedTiles = new Boolean[world.getWidth()][world.getHeight()];
        nodes = new Node[world.getWidth()][world.getHeight()];

        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                nodes[x][y] = new Node(x, y);
            }
        }
    }

    public ArrayList<Tile> findPath(int startX, int startY, int targetX, int targetY) {

        try {
            if (isBlockedTile(world.getTile(targetX, targetY))) {

                return null;
            }
        } catch (TileOutOfBoundsException ignored) {
        }

        nodes[startX][startY].cost = 0;
        nodes[startX][startY].depth = 0;
        closed.clear();
        open.clear();
        open.add(nodes[startX][startY]);
        nodes[targetX][targetY].parent = null;
        int maxDepth = 0;

        while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
            Node current = getFirstInOpen();
            if (current == nodes[targetX][targetY]) {
                break;
            }

            removeFromOpen(current);
            addToClosed(current);

            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    if (x == 0 && y == 0) {
                        continue;
                    }

//                    if (x !=0 && y != 0) { // No diagonal movement.
//                        continue;
//                    }

                    int xp = x + current.x;
                    int yp = y + current.y;

                    if (isValidLocation(startX, startY, xp, yp)) {
                        float nextStepCost = current.cost + getTilePathCost(current.x, current.y, xp, yp);
                        Node neighbor = nodes[xp][yp];
                        setVisitedTile(xp, yp);

                        if (nextStepCost < neighbor.cost) {
                            if (inOpenList((neighbor))) {
                                removeFromOpen(neighbor);
                            }
                            if (inClosedList(neighbor)) {
                                removeFromClosed(neighbor);
                            }
                        }
                        if (!inOpenList(neighbor) && !inClosedList(neighbor)) {
                            neighbor.cost = nextStepCost;
                            neighbor.heuristic = getHeuristicCost(xp, yp, targetX, targetY);
                            maxDepth = Math.max(maxDepth, neighbor.setParent(current));
                            addToOpen(neighbor);
                        }
                    }
                }
            }
        }
        if (nodes[targetX][targetY].parent == null) {
            return null;
        }
        ArrayList<Tile> t = null;
        try {
            t = new ArrayList<>();
            Node target = nodes[targetX][targetY];
            while (target != nodes[startX][startY]) {
                t.add(0, world.getTile(target.x, target.y));
                target = target.parent;
            }
            t.add(0, world.getTile(startX, startY));
        } catch (TileOutOfBoundsException ignored) {

        }
        return t;
    }


    Node getFirstInOpen() {
        return (Node) open.first();
    }

    void addToOpen(Node node) {
        open.add(node);
    }

    boolean inOpenList(Node node) {
        return open.contains(node);
    }

    void removeFromOpen(Node node) {
        open.remove(node);
    }

    void addToClosed(Node node) {
        closed.add(node);
    }

    boolean inClosedList(Node node) {
        return closed.contains(node);
    }

    void removeFromClosed(Node node) {
        closed.remove(node);
    }

    private boolean isBlockedTile(Tile tile) {
        if (tile.getOccupyingObject() != null) {
            return true;
        }
        return tile.getTerrain() instanceof Water;
    }

    boolean isValidLocation(int startX, int startY, int x, int y) {
        boolean invalid = (x < 0) || (y < 0) || (x >= world.getWidth()) || (y >= world.getHeight());

        if ((!invalid) && ((startX != x) || (startY != y))) {
            try {
                return !isBlockedTile(world.getTile(x, y));
            } catch (TileOutOfBoundsException ignored) {
            }
        }
        return !invalid;
    }

    private float getTilePathCost(int x, int y, int targetX, int targetY) {
        try {
            Tile current = world.getTile(x, y);
            Tile target = world.getTile(targetX, targetY);
            if (current.getTerrain() instanceof Grass) {
                if (target.getTerrain() instanceof Grass) {
                    return 1.0f;
                } else {
                    return 0.5f;
                }
            }
        } catch (TileOutOfBoundsException ignored) {
        }
        return 0.0f;

    }

    private void setVisitedTile(int x, int y) {
        visitedTiles[x][y] = true;
    }

    public boolean visitedTile(int x, int y) {
        return visitedTiles[x][y];
    }

    public void clearVisitedTiles() {
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                visitedTiles[x][y] = false;
            }
        }
    }


    private float getHeuristicCost(int x, int y, int targetX, int targetY) {
        //Manhattan
        float dx = Math.abs(targetX - x);
        float dy = Math.abs(targetY - y);
        return dx + dy;
        //Euclidean
//        float dx = targetX - x;
//        float dy = targetY - y;
//        float result = (float) (Math.sqrt((dx * dx) + (dy * dy)));
//        return result;
    }

}
