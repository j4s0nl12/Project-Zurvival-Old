package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Game.Zurvival;

import static com.mygdx.game.Game.Zurvival.screenList;
import static com.mygdx.game.Game.Zurvival.lastScreen;
import static com.mygdx.game.Game.Zurvival.MAINMENUSCREEN;
import static com.mygdx.game.Game.Zurvival.DAYGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.PREPAREGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.NIGHTGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.OPTIONSSCREEN;
import static com.mygdx.game.Game.Zurvival.STATISTICSSCREEN;
import static com.mygdx.game.Game.Zurvival.getNewScreen;

public class StatisticsScreen implements Screen{
	
	final Zurvival game;
	OrthographicCamera camera;
	
	private long time;
	private long lastTouchedTime;
	public long delay;
	
	Texture backImg;
	Rectangle backBound;
	
	public StatisticsScreen(final Zurvival gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());//Look at Desktop Launcher for Desktop Resolution
		
		backImg = new Texture("Images/Menus/Back.png");
		backBound = new Rectangle(Gdx.graphics.getWidth()/2 - backImg.getWidth()/2, 300, backImg.getWidth(), backImg.getHeight());
		
		time = System.currentTimeMillis();
		lastTouchedTime = time;
		delay = 250L;
	}
	
	@Override
	public void show() {
		delay = 250L;
		lastTouchedTime = System.currentTimeMillis();
	}

	@Override
	public void render(float delta) {
		time = System.currentTimeMillis();
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))//For simple exits
			Gdx.app.exit();
		
		Gdx.gl.glClearColor(0.15f, 0.2f, 0.15f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(backImg, backBound.getX(), backBound.getY());
		game.batch.end();
		
		if(Gdx.input.isTouched() && time >= lastTouchedTime + delay){
			delay = 100L;
			lastTouchedTime = System.currentTimeMillis();
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
			camera.unproject(touchPos);
			
			if(backBound.contains(touchPos.x,touchPos.y)){
				game.setScreen(screenList.get(lastScreen));
				//game.setScreen(getNewScreen(lastScreen,game));
				lastScreen = STATISTICSSCREEN;
			}
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
		// TODO Auto-generated method stub
		
	}
}
