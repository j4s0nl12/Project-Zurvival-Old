package com.mygdx.game.robin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    //final static float SCALE = 1f;

    Zurvival mGame;
    TiledMap tiledMap;
    OrthographicCamera camera;
    Viewport viewport;
    SpriteBatch batch;
    CustomRenderer renderer;
    TiledMapTileLayer mapObjects;
    ShapeRenderer debugRenderer;

    int columns;
    int rows;
    float mapWidth;
    float mapHeight;
    float tileSize;

    Animation animation;
    TextureAtlas textureAtlas;
    float elapsedTime;
    boolean isFlipped;

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
        renderer = new CustomRenderer(tiledMap, camera);
        Gdx.input.setInputProcessor(this);

        mapObjects = (TiledMapTileLayer)tiledMap.getLayers().get("map objects");

        columns = mapObjects.getWidth();
        rows = mapObjects.getHeight();
        tileSize = mapObjects.getTileHeight();
        mapWidth = columns * tileSize;
        mapHeight = rows * tileSize;

        camera.position.set(new Vector3(mapWidth/2, mapHeight/2, 0));
        camera.zoom = 0.1f;

        textureAtlas = new TextureAtlas(Gdx.files.internal("Sprites/sheep-packed/pack.atlas"));
        animation = new Animation(1f/15f, textureAtlas.getRegions());

        renderer.addAnimation(animation);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += delta;

        camera.update();
        renderer.setView(camera);
        renderer.render();

//        batch.begin();
//        TextureRegion region = animation.getKeyFrame(elapsedTime, true);
        //region.flip(isFlipped, false);

//        batch.draw(region,
//                camera.position.x, camera.position.y,
//                0, 0,
//                region.getRegionWidth(), region.getRegionHeight(),
//                0.3f, 0.3f,
//                0);
//        batch.end();

        drawGrid();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        if (keycode == Input.Keys.LEFT) {
            isFlipped = true;
            camera.translate(-5, 0);
        }
        if (keycode == Input.Keys.RIGHT) {
            isFlipped = false;
            camera.translate(5, 0);
        }
        if(keycode == Input.Keys.UP)
            camera.translate(0,5);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,-5);
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

            for(int i = 0; i <= rows; i++){
                Vector2 start = new Vector2(0, i*tileSize);
                Vector2 end = new Vector2(mapWidth, i*tileSize);
                drawDebugLine(start, end, camera.combined);
            }
            for(int i = 0; i <= columns; i++){
                Vector2 start = new Vector2(i*tileSize, 0);
                Vector2 end = new Vector2(i*tileSize, mapHeight);
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
