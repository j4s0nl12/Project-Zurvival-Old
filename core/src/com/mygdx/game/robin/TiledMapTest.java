package com.mygdx.game.robin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game.Zurvival;

/**
 * Created by robin on 6/17/2016.
 */
public class TiledMapTest extends InputAdapter implements Screen {

    final static float SCALE = 3f;

    Zurvival mGame;
    TiledMap tiledMap;
    OrthographicCamera camera;
    Viewport viewport;
    SpriteBatch batch;
    OrthogonalTiledMapRenderer renderer;
    TiledMapTileLayer mapObjects;
    ShapeRenderer debugRenderer;
    Vector3 center;

    int columns;
    int rows;
    float tileSize;

    public TiledMapTest(Zurvival game) {
        mGame = game;
    }

    @Override
    public void show() {
        tiledMap = new TmxMapLoader().load("maps/test.tmx");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        debugRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        renderer = new OrthogonalTiledMapRenderer(tiledMap, SCALE);
        Gdx.input.setInputProcessor(this);

        mapObjects = (TiledMapTileLayer)tiledMap.getLayers().get("map objects");

        columns = mapObjects.getWidth();
        rows = mapObjects.getHeight();
        tileSize = mapObjects.getTileHeight();

        //TODO can't seem to default a center for camera
        center = new Vector3(Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()/2, 0);
        camera.position.set(center);
        camera.translate(-32, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        drawGrid();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(width/2f, height/2f, 0);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)
            camera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,-32);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,32);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }

    private void drawGrid(){
        if(tiledMap != null){
            ShapeRenderer sr = new ShapeRenderer();
            sr.setColor(Color.WHITE);
            sr.setProjectionMatrix(camera.combined);

            sr.begin(ShapeRenderer.ShapeType.Line);

            float width = tileSize * columns * SCALE;
            float height = tileSize * rows * SCALE;

            for(int i = 0; i <= rows; i++){
                Vector2 start = new Vector2(0, i*tileSize * SCALE);
                Vector2 end = new Vector2(width, i*tileSize * SCALE);
                drawDebugLine(start, end, camera.combined);
            }
            for(int i = 0; i <= columns; i++){
                Vector2 start = new Vector2(i*tileSize * SCALE, 0);
                Vector2 end = new Vector2(i*tileSize * SCALE, height);
                drawDebugLine(start, end, camera.combined);
            }
            sr.end();
        }
    }

    private void drawDebugLine(Vector2 start, Vector2 end, Matrix4 projectionMatrix)
    {
        Gdx.gl.glLineWidth(2);
        debugRenderer.setProjectionMatrix(projectionMatrix);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(Color.WHITE);
        debugRenderer.line(start, end);
        debugRenderer.end();
        Gdx.gl.glLineWidth(1);
    }
}
