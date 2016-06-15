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
import com.mygdx.game.Player.Player;

import static com.mygdx.game.Game.Zurvival.screenList;
import static com.mygdx.game.Game.Zurvival.lastScreen;
import static com.mygdx.game.Game.Zurvival.MAINMENUSCREEN;
import static com.mygdx.game.Game.Zurvival.DAYGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.PREPAREGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.NIGHTGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.OPTIONSSCREEN;
import static com.mygdx.game.Game.Zurvival.STATISTICSSCREEN;
import static com.mygdx.game.Game.Zurvival.getNewScreen;

public class NightGameScreen implements Screen{
	
	final Zurvival game;
	OrthographicCamera camera;
	
	private long time;
	private long lastTouchedTime;
	public long delay;
	
	Player player;
	
	//Textures
	Texture backImg;
	Texture upArrowImg;
	Texture downArrowImg;
	Texture rightArrowImg;
	Texture leftArrowImg;
	
	//Rectangles
	Rectangle backBound;
	Rectangle upArrowBound;
	Rectangle downArrowBound;
	Rectangle rightArrowBound;
	Rectangle leftArrowBound;
	
	boolean upArrowTouched;
	boolean downArrowTouched;
	boolean rightArrowTouched;
	boolean leftArrowTouched;
	
	public NightGameScreen(final Zurvival gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());//Look at Desktop Launcher for Desktop Resolution
		
		backImg = new Texture("Images/Menus/Back.png");
		upArrowImg = new Texture("Sprites/Up Arrow.png");
		downArrowImg = new Texture("Sprites/Down Arrow.png");
		rightArrowImg = new Texture("Sprites/Right Arrow.png");
		leftArrowImg = new Texture("Sprites/Left Arrow.png");
		
		backBound = new Rectangle(Gdx.graphics.getWidth()/2 - backImg.getWidth()/2, 300, backImg.getWidth(), backImg.getHeight());
		//upArrowBound = new Rectangle(rightArrowImg.getWidth()/2, 300, upArrowImg.getWidth(), upArrowImg.getHeight());
		//downArrowBound = new Rectangle(150, 100, downArrowImg.getWidth(), downArrowImg.getHeight());
		//rightArrowBound = new Rectangle(leftArrowImg.getWidth() + upArrowImg.getWidth()/2, 200, rightArrowImg.getWidth(), rightArrowImg.getHeight());
		//leftArrowBound = new Rectangle(0, 200, leftArrowImg.getWidth(), leftArrowImg.getHeight());
		
		int centerX = 150;
		int centerY = 150;
		float div = 1.3f;
		
		upArrowBound = new Rectangle(centerX, centerY + upArrowImg.getHeight()/div, upArrowImg.getWidth(), upArrowImg.getHeight());
		downArrowBound = new Rectangle(centerX, centerY - downArrowImg.getHeight()/div, downArrowImg.getWidth(), downArrowImg.getHeight());
		rightArrowBound = new Rectangle(centerX + rightArrowImg.getWidth()/div, centerY, rightArrowImg.getWidth(), rightArrowImg.getHeight());
		leftArrowBound = new Rectangle(centerX - leftArrowImg.getWidth()/div, centerY, leftArrowImg.getWidth(), leftArrowImg.getHeight());
		
		time = System.currentTimeMillis();
		lastTouchedTime = time;
		delay = 250L;
		
		upArrowTouched = false;
		downArrowTouched = false;
		rightArrowTouched = false;
		leftArrowTouched = false;
		
		player = new Player(0,0);
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
		
		player.update(delta);
		
		game.batch.begin();
		game.batch.draw(player.img, player.getX(), player.getY());
		game.batch.draw(backImg, backBound.getX(), backBound.getY());
		game.batch.draw(upArrowImg, upArrowBound.getX(), upArrowBound.getY());
		game.batch.draw(downArrowImg, downArrowBound.getX(), downArrowBound.getY());
		game.batch.draw(rightArrowImg, rightArrowBound.getX(), rightArrowBound.getY());
		game.batch.draw(leftArrowImg, leftArrowBound.getX(), leftArrowBound.getY());
		game.batch.end();
		
		if(Gdx.input.isTouched() && time >= lastTouchedTime + delay){
			delay = 100L;
			lastTouchedTime = System.currentTimeMillis();
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
			camera.unproject(touchPos);
			
			if(backBound.contains(touchPos.x,touchPos.y)){
				game.setScreen(screenList.get(lastScreen));
				lastScreen = NIGHTGAMESCREEN;
			}
			
			if(upArrowBound.contains(touchPos.x, touchPos.y) && !upArrowTouched){
				upArrowTouched = true;
				player.moveUp();
			}
			
			if(downArrowBound.contains(touchPos.x, touchPos.y) && !downArrowTouched){
				downArrowTouched = true;
				player.moveDown();
			}
			
			if(rightArrowBound.contains(touchPos.x, touchPos.y) && !rightArrowTouched){
				rightArrowTouched = true;
				player.moveRight();
			}
			
			if(leftArrowBound.contains(touchPos.x, touchPos.y) && !leftArrowTouched){
				leftArrowTouched = true;
				player.moveLeft();
			}
		}
		
		if(!Gdx.input.isTouched()){
			upArrowTouched = false;
			downArrowTouched = false;
			rightArrowTouched = false;
			leftArrowTouched = false;
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
