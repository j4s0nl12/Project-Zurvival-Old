package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class MainMenuScreen implements Screen{
	
	final Zurvival game;
	OrthographicCamera camera;
	Array<BulletDentParticle> pList;
	private long time;
	private long lastTouchedTime;
	
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
	
	//Sounds
	Sound srsSound;//Single Rifle Shot
	
	MainMenuScreen(final Zurvival gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());//Look at Desktop Launcher for Desktop Resolution
		pList = new Array<>();
		
		titleImg = new Texture("Images/Menus/Title.png");
		newgameImg = new Texture("Images/Menus/New Game.png");
		continueImg = new Texture("Images/Menus/Continue.png");
		optionsImg = new Texture("Images/Menus/Options.png");
		statImg = new Texture("Images/Menus/Statistics.png");
		
		newgameBound = new Rectangle(Gdx.graphics.getWidth()/2 - newgameImg.getWidth()/2, 300, newgameImg.getWidth(), newgameImg.getHeight());
		continueBound = new Rectangle(Gdx.graphics.getWidth()/2 - continueImg.getWidth()/2, 300, continueImg.getWidth(), continueImg.getHeight());
		optionsBound = new Rectangle(Gdx.graphics.getWidth()/2 - optionsImg.getWidth()/2, 200, optionsImg.getWidth(), optionsImg.getHeight());
		statBound = new Rectangle(Gdx.graphics.getWidth()/2 - statImg.getWidth()/2, 100, statImg.getWidth(), statImg.getHeight());
		
		srsSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Single Rifle Shot.mp3"));
		time = System.currentTimeMillis();
		lastTouchedTime = time;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
		
		for(int i = 0; i < pList.size; i++){
			pList.get(i).update(delta);
			if(!pList.get(i).isAlive){
				pList.removeIndex(i);
			}
		}
		
		game.batch.begin();
		game.batch.draw(titleImg, Gdx.graphics.getWidth()/2 - titleImg.getWidth()/2, Gdx.graphics.getHeight() - 350);
		game.batch.draw(newgameImg, newgameBound.getX(), 300);
		//game.batch.draw(continueImg, continueBound.getX(), 300);
		game.batch.draw(optionsImg, optionsBound.getX(), 200);
		game.batch.draw(statImg, statBound.getX(), 100);
		for(BulletDentParticle p : pList){
			game.batch.draw(p.img, p.x - p.img.getWidth()/2, p.y - p.img.getHeight()/2);
		}
		game.batch.end();
		
		if(Gdx.input.isTouched() && time >= lastTouchedTime + 100L){
			lastTouchedTime = System.currentTimeMillis();
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
			camera.unproject(touchPos);
			pList.add(new BulletDentParticle(touchPos.x, touchPos.y));
			srsSound.play();
			
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
		titleImg.dispose();
		newgameImg.dispose();
		continueImg.dispose();
		optionsImg.dispose();
		statImg.dispose();
		srsSound.dispose();
	}
}