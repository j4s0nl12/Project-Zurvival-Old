package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen{
	
	final Zurvival game;
	OrthographicCamera camera;
	
	//Images
	Texture titleImg;
	Texture newgameImg;
	Texture continueImg;
	Texture optionsImg;
	Texture statImg;
	
	//Image bounds
	Rectangle newgameBound;
	Rectangle continueBound;
	Rectangle optionsBound;
	Rectangle statBound;
	
	MainMenuScreen(final Zurvival gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());//Look at Desktop Launcher for Desktop Resolution
		
		titleImg = new Texture("Images/Menus/Title.png");
		newgameImg = new Texture("Images/Menus/New Game.png");
		continueImg = new Texture("Images/Menus/Continue.png");
		optionsImg = new Texture("Images/Menus/Options.png");
		statImg = new Texture("Images/Menus/Statistics.png");
		
		newgameBound = new Rectangle(Gdx.graphics.getWidth()/2 - newgameImg.getWidth()/2, 300, newgameImg.getWidth(), newgameImg.getHeight());
		continueBound = new Rectangle(Gdx.graphics.getWidth()/2 - continueImg.getWidth()/2, 300, continueImg.getWidth(), continueImg.getHeight());
		optionsBound = new Rectangle(Gdx.graphics.getWidth()/2 - optionsImg.getWidth()/2, 200, optionsImg.getWidth(), optionsImg.getHeight());
		statBound = new Rectangle(Gdx.graphics.getWidth()/2 - statImg.getWidth()/2, 100, statImg.getWidth(), statImg.getHeight());
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))//For simple exits
			Gdx.app.exit();
			
		Gdx.gl.glClearColor(0.15f, 0.2f, 0.15f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(titleImg, Gdx.graphics.getWidth()/2 - titleImg.getWidth()/2, Gdx.graphics.getHeight() - 350);
		game.batch.draw(newgameImg, newgameBound.getX(), 300);
		//game.batch.draw(continueImg, continueBound.getX(), 300);
		game.batch.draw(optionsImg, optionsBound.getX(), 200);
		game.batch.draw(statImg, statBound.getX(), 100);
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
			camera.unproject(touchPos);
			
			//New Game
			if(newgameBound.contains(touchPos.x,touchPos.y)){
				
			}
			
			//Continue
			if(continueBound.contains(touchPos.x, touchPos.y)){
				
			}
			
			//Options
			if(optionsBound.contains(touchPos.x, touchPos.y)){
				
			}
			
			//Statistics
			if(statBound.contains(touchPos.x, touchPos.y)){
				
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
