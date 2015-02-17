package com.fuzzy.autocity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.Ray;
import com.fuzzy.autocity.terrain.*;
import com.fuzzy.autocity.world.resources.prefabs.Tree;

public class IsoCamTest extends AutocityGDX implements InputProcessor {
    Texture grassTexture;
    Texture sandTexture;
    Texture waterTexture;
    Texture riverTexture;
    Texture stoneTexture;
    Texture testBuildingTexture;
    Texture testTreeTexture;
    OrthographicCamera cam;
    SpriteBatch batch;
    Sprite[][] terrainTiles;
    Sprite[][] worldObjectTiles;
    float effectiveViewportWidth;
    float effectiveViewportHeight;

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
        cam = new OrthographicCamera(100, 100 * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth()));
        effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        effectiveViewportHeight = cam.viewportHeight * cam.zoom;
        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100 / cam.viewportWidth);
        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
        cam.position.z = 100;
        cam.direction.set(-1, -1, -1);
        cam.near = 1;
        cam.far = 1000;
        cam.update();


        terrainTiles = new Sprite[game.getWorld().getWidth()][game.getWorld().getHeight()];
        worldObjectTiles = new Sprite[game.getWorld().getWidth()][game.getWorld().getHeight()];

        grassTexture = new Texture(Gdx.files.internal("dev_grass.png"));
        waterTexture = new Texture(Gdx.files.internal("dev_water.png"));
        sandTexture = new Texture(Gdx.files.internal("dev_sand.png"));
        riverTexture = new Texture(Gdx.files.internal("dev_river.png"));
        stoneTexture = new Texture(Gdx.files.internal("dev_stone.png"));
        testBuildingTexture = new Texture(Gdx.files.internal("blue_cube.png"));
        testTreeTexture = new Texture(Gdx.files.internal("green_cube.png"));


        terrainMatrix.setToRotation(new Vector3(-1, 0, 0), 90);
        worldObjectMatrix.setToRotation(new Vector3(), 0);

        for (int z = 0; z < game.getWorld().getHeight(); z++) {
            for (int x = 0; x < game.getWorld().getWidth(); x++) {
                Tile tile = game.getWorld().getTile(x, z);
                float heightTint = Math.max(tile.getHeight(), 1);

                if (tile.getTerrain() instanceof Grass) {
                    terrainTiles[x][z] = new Sprite(grassTexture);
                } else if (tile.getTerrain() instanceof Sand) {
                    terrainTiles[x][z] = new Sprite(sandTexture);
                } else if (tile.getTerrain() instanceof Water) {
                    terrainTiles[x][z] = new Sprite(waterTexture);
                } else if (tile.getTerrain() instanceof River) {
                    terrainTiles[x][z] = new Sprite(riverTexture);
                } else if (tile.getTerrain() instanceof Mountain) {
                    terrainTiles[x][z] = new Sprite(stoneTexture);
                } else {
                    terrainTiles[x][z] = new Sprite(grassTexture); // Fallback for new terrain types
                }
                terrainTiles[x][z].setPosition(x, z);
                terrainTiles[x][z].setSize(1, 1);
                terrainTiles[x][z].setColor(heightTint, heightTint, heightTint, 1.0f);
                if (tile.getOccupyingObject() != null) {
                    if (tile.getOccupyingObject() instanceof Tree) {
                        worldObjectTiles[x][z] = new Sprite(testTreeTexture);
                    } else {
                        worldObjectTiles[x][z] = new Sprite(testBuildingTexture);
                    }
                    worldObjectTiles[x][z].setPosition(x, z);
                    worldObjectTiles[x][z].setSize(1, 1);
                }
            }
        }

        batch = new

                SpriteBatch();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();

        batch.setProjectionMatrix(cam.combined);
        batch.setTransformMatrix(terrainMatrix);
        batch.begin();

        for (int z = 0; z < game.getWorld().getHeight(); z++) {
            for (int x = 0; x < game.getWorld().getWidth(); x++) {
                terrainTiles[x][z].draw(batch);

                if (worldObjectTiles[x][z] != null) {
                    worldObjectTiles[x][z].draw(batch);
                }
            }
        }


        batch.end();

//        checkTileTouched();
    }

    private void checkTileTouched() {
        if (Gdx.input.justTouched()) {
            Ray pickRay = cam.getPickRay(Gdx.input.getX(), Gdx.input.getY());
            Intersector.intersectRayPlane(pickRay, xzPlane, intersection);
            int x = (int) intersection.x;
            int z = (int) intersection.z;
            if (x >= 0 && x < game.getWorld().getWidth() && z >= 0 && z < game.getWorld().getHeight()) {
                if (lastSelectedTile != null) lastSelectedTile.setColor(1, 1, 1, 1);
                Sprite sprite = terrainTiles[x][z];
                sprite.setColor(1, 0, 0, 1);
                lastSelectedTile = sprite;
            }
        }
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        Ray pickRay = cam.getPickRay(x, y);
        Intersector.intersectRayPlane(pickRay, xzPlane, curr);

        if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
            pickRay = cam.getPickRay(last.x, last.y);
            Intersector.intersectRayPlane(pickRay, xzPlane, delta);
            delta.sub(curr);
            cam.position.add(delta.x, delta.y, delta.z);
        }
        last.set(x, y, 0);
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

    @Override
    public boolean keyTyped(char chr) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            cam.position.set(0, 0, 100);
        }
        effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        effectiveViewportHeight = cam.viewportHeight * cam.zoom;
        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100 / cam.viewportWidth);
        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);

        cam.update();
        return false;
    }

    @Override
    public boolean keyUp(int e) {
        return false;
    }

    @Override
    public boolean keyDown(int e) {
        return false;
    }

    @Override
    public boolean mouseMoved(int e, int t) {
        return false;
    }

    @Override
    public boolean scrolled(int e) {
        return false;
    }
}