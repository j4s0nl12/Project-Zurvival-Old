package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game.Zurvival;

/**
 * Created by robin on 6/13/2016.
 */
public class RobinFunsiesScreen implements Screen {

    final Zurvival mGame;
    OrthographicCamera camera;
    ParticleEffect pe;

    public RobinFunsiesScreen(final Zurvival game){
        mGame = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("Images/Particles/BulletFire.particle"),  Gdx.files.internal("Images/Particles/"));
        pe.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        pe.start();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = mGame.batch;

        batch.begin();
        pe.update(Gdx.graphics.getDeltaTime());
        pe.draw(batch);
        batch.end();

        if(pe.isComplete()){
            pe.reset();
        }
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

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
    }
}
