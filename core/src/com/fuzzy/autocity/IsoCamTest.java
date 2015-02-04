package com.fuzzy.autocity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.Ray;

public class IsoCamTest extends AutocityGDX implements InputProcessor {
    Texture texture;
    Texture testBuildingTexture;
    OrthographicCamera cam;
    SpriteBatch batch;
    Sprite[][] terrainTiles;
    Sprite[][] worldObjectTiles;

    final Matrix4 terrainMatrix = new Matrix4();
    final Matrix4 worldObjectMatrix = new Matrix4();
    final Plane xzPlane = new Plane(new Vector3(0, 1, 0), 0);
    final Vector3 intersection = new Vector3();
    Sprite lastSelectedTile = null;
    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();

    @Override
    public void create() {
        // Start game instance
        game = new Game();
        game.start();

        // Configure the camera
        cam = new OrthographicCamera(20, 20 * (Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth()));
        cam.position.set(5, 5, 10);
        cam.direction.set(-1, -1, -1);
        cam.near = 1;
        cam.far = 100;

        terrainTiles = new Sprite[game.getWorld().getWidth()][game.getWorld().getHeight()];
        worldObjectTiles = new Sprite[game.getWorld().getWidth()][game.getWorld().getHeight()];

        texture = new Texture(Gdx.files.internal("dev_grass.png"));
        testBuildingTexture = new Texture(Gdx.files.internal("blue_cube.png"));

        terrainMatrix.setToRotation(new Vector3(1, 0, 0), 90);
        worldObjectMatrix.setToRotation(new Vector3(), 0);

        for (int z = 0; z < game.getWorld().getHeight(); z++) {
            for (int x = 0; x < game.getWorld().getWidth(); x++) {
                terrainTiles[x][z] = new Sprite(texture);
                terrainTiles[x][z].setPosition(x, z);
                terrainTiles[x][z].setSize(1, 1);

                Tile tile = game.getWorld().getTile(x, z);

                if (tile.getOccupyingObject() != null) {
                    worldObjectTiles[x][z] = new Sprite(testBuildingTexture);
                    worldObjectTiles[x][z].setPosition(x, z);
                    worldObjectTiles[x][z].setSize(1, 1);
                }
            }
        }

        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();

        batch.setProjectionMatrix(cam.combined);
        batch.setTransformMatrix(terrainMatrix);
        batch.begin();

        for(int z = 0; z < game.getWorld().getHeight(); z++) {
            for(int x = 0; x < game.getWorld().getWidth(); x++) {
                terrainTiles[x][z].draw(batch);

                if (worldObjectTiles[x][z] != null) {
                    worldObjectTiles[x][z].draw(batch);
                }
            }
        }



        batch.end();

        checkTileTouched();
    }

    private void checkTileTouched() {
        if(Gdx.input.justTouched()) {
            Ray pickRay = cam.getPickRay(Gdx.input.getX(), Gdx.input.getY());
            Intersector.intersectRayPlane(pickRay, xzPlane, intersection);
            int x = (int)intersection.x;
            int z = (int)intersection.z;
            if(x >= 0 && x < game.getWorld().getWidth() && z >= 0 && z < game.getWorld().getHeight()) {
                if(lastSelectedTile != null) lastSelectedTile.setColor(1, 1, 1, 1);
                Sprite sprite = terrainTiles[x][z];
                sprite.setColor(1, 0, 0, 1);
                lastSelectedTile = sprite;
            }
        }
    }

    @Override public boolean touchDragged (int x, int y, int pointer) {
        Ray pickRay = cam.getPickRay(x, y);
        Intersector.intersectRayPlane(pickRay, xzPlane, curr);

        if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
            pickRay = cam.getPickRay(last.x, last.y);
            Intersector.intersectRayPlane(pickRay, xzPlane, delta);
            delta.sub(curr);
            cam.position.add(delta.x, delta.y, delta.z);
        }
        last.set(x, y, 0);
        return false;
    }

    @Override public boolean touchUp(int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

    @Override public boolean touchDown(int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

    @Override public boolean keyTyped(char chr) {
        return false;
    }

    @Override public boolean keyUp(int e) {
        return false;
    }

    @Override public boolean keyDown(int e) {
        return false;
    }

    @Override public boolean mouseMoved(int e, int t) {
        return false;
    }

    @Override public boolean scrolled(int e) {
        return false;
    }
}