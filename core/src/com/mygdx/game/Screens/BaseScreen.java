package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Game.Zurvival;

public class BaseScreen extends InputAdapter implements Screen{
	
	final Zurvival game;
	
	public long time;
	public long lastTouchedTime;
	public long delay;
	
	public BaseScreen(final Zurvival gam){
		game = gam;
		
		//game.viewport.apply();
		
		this.time = System.currentTimeMillis();
		this.lastTouchedTime = time;
		this.delay = 250L;
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		this.delay = 250L;
		this.lastTouchedTime = System.currentTimeMillis();
	}

	@Override
	public void render(float delta) {
		this.time = System.currentTimeMillis();
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
		
		Gdx.gl.glClearColor(0.15f, 0.2f, 0.15f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.camera.update();
		game.batch.setProjectionMatrix(game.camera.combined);
		
	}

	@Override
	public void resize(int width, int height) {
		game.resize(width, height);
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
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public Vector3 getProjectAt(int screenX, int screenY){
		return game.camera.unproject(new Vector3(screenX, screenY, 0));
	}

}
