package com.mygdx.game.robin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Game.Zurvival;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by robin on 6/13/2016.
 */
public class RobinFunsiesScreen extends InputAdapter implements Screen {

    final Zurvival mGame;
    final static String TAG = RobinFunsiesScreen.class.getSimpleName();
    OrthographicCamera camera;
    ParticleEffect pe;
    TextureAtlas textureAtlas;
    SpriteBatch batch;
    ExtendViewport viewport;
    Texture texture;
    Sprite sprite;
    BitmapFont font = new BitmapFont();

    static final float TIME_STEP = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;

    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    World world;
    Box2DDebugRenderer debugRenderer;
    Array<Body> bodies = new Array<Body>();

    private float accumulator = 0;

    private void doPhysicsStep(float deltaTime) {
        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            accumulator -= TIME_STEP;
        }
    }

    public RobinFunsiesScreen(final Zurvival game){
        mGame = game;
    }

    @Override
    public void show() {
        Box2D.init();

        world = new World(new Vector2(0, -25f), true);
        debugRenderer = new Box2DDebugRenderer();

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        textureAtlas = new TextureAtlas("sprites-packed/pack.atlas");
        texture = new Texture(Gdx.files.internal("Sprites/sheep0001.png"));

        sprite = new Sprite(texture);

        addSprites();

        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("Images/Particles/BulletFire.particle"),  Gdx.files.internal("Images/Particles/"));
        pe.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        pe.start();

        createSheep(500, 300);

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(500, 0 );
        Body box = world.createBody(bd);
        box.setUserData("ground");

        PolygonShape rect = new PolygonShape();
        rect.setAsBox(1000, 20);

        FixtureDef fd = new FixtureDef();
        fd.shape = rect;
        fd.density = 0.5f;
        fd.friction = 0.4f;
        fd.restitution = 0.6f;
        box.createFixture(fd);

        rect.dispose();

        sprite.setOrigin(0, 0);

        Gdx.input.setInputProcessor(this);

        world.getBodies(bodies);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        /*
            physics stuffs
        */
        for (Body b : bodies) {
            if(b!=null){
                Object o = b.getUserData();
                if(o instanceof Sprite){
                    Sprite s = (Sprite) o;
                    s.setPosition(b.getPosition().x, b.getPosition().y);
                    s.setRotation(MathUtils.radiansToDegrees * b.getAngle());
                    sprite.draw(batch);
                }
            }
        }

        pe.update(Gdx.graphics.getDeltaTime());
        pe.draw(batch);

        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, Gdx.graphics.getHeight()-10);
        batch.end();

        if(pe.isComplete()){
            pe.reset();
        }
        debugRenderer.render(world, camera.combined);

        // recommended at end of render
        doPhysicsStep(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
        world.dispose();
    }

    private void drawSprite(String name, float x, float y) {
        Sprite sprite = sprites.get(name);

        sprite.setPosition(x, y);

        sprite.draw(batch);
    }

    private void addSprites() {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

        for (TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log(TAG, "touch");
        screenY = Gdx.graphics.getHeight() - screenY;
        createSheep(screenX, screenY);
        return true;
    }

    @Override
    public boolean keyDown(int keyCode){
        if(keyCode == Input.Keys.C){
            mGame.setScreen(new CameraTest(mGame));
        }
        return true;
    }

    private void createSheep(float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y );
        Body sheep = world.createBody(bodyDef);
        sheep.setUserData(sprite);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("Sprites/sheep0001.json"));
        loader.attachFixture(sheep, "sheep", fixtureDef, sprite.getWidth());

//        Random random = new Random();
//        float angle = (float) random.nextInt() * MathUtils.degreesToRadians;
//        sheep.setTransform(x, y, angle);

        world.getBodies(bodies);
    }
}
