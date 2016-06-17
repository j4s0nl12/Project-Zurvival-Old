package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Game.Zurvival;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.Projectile;

import static com.mygdx.game.Game.Zurvival.screenList;
import static com.mygdx.game.Game.Zurvival.lastScreen;
import static com.mygdx.game.Game.Zurvival.MAINMENUSCREEN;
import static com.mygdx.game.Game.Zurvival.DAYGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.PREPAREGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.NIGHTGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.OPTIONSSCREEN;
import static com.mygdx.game.Game.Zurvival.STATISTICSSCREEN;

public class NightGameScreen extends BaseScreen{
	
	final String TAG = NightGameScreen.class.getSimpleName();
	
	Player player;
	
	//Images
	Sprite backImg;
	Sprite upArrowImg;
	Sprite downArrowImg;
	Sprite rightArrowImg;
	Sprite leftArrowImg;
	
	public NightGameScreen(final Zurvival gam){
		super(gam);
		
		backImg = new Sprite(new Texture("Images/Menus/Back.png"));
		upArrowImg = new Sprite(new Texture("Sprites/Up Arrow.png"));
		downArrowImg = new Sprite(new Texture("Sprites/Down Arrow.png"));
		rightArrowImg = new Sprite(new Texture("Sprites/Right Arrow.png"));
		leftArrowImg = new Sprite(new Texture("Sprites/Left Arrow.png"));

		int centerX = 150;
		int centerY = 150;
		float div = 1.3f;
		
		backImg.setPosition(game.GAME_WORLD_WIDTH/2 - backImg.getWidth()/2, game.GAME_WORLD_HEIGHT/2 - backImg.getHeight()/2);
		upArrowImg.setPosition(centerX, centerY + upArrowImg.getHeight()/div);
		downArrowImg.setPosition(centerX, centerY - downArrowImg.getHeight()/div);
		rightArrowImg.setPosition(centerX + rightArrowImg.getWidth()/div, centerY);
		leftArrowImg.setPosition(centerX - leftArrowImg.getWidth()/div, centerY);
		
		player = new Player(game.GAME_WORLD_WIDTH/2, game.GAME_WORLD_HEIGHT/2);
	}
	
	@Override
	public void show() {
		super.show();
		player.setFireRate(300L);
		player.setLastFiredTime(lastTouchedTime);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		player.update(delta);
		
		game.batch.begin();
		player.img.draw(game.batch);
		for(Projectile p : player.bList){
			p.img.draw(game.batch);
		}
		backImg.draw(game.batch);
		upArrowImg.draw(game.batch);
		downArrowImg.draw(game.batch);
		rightArrowImg.draw(game.batch);
		leftArrowImg.draw(game.batch);
		
		game.batch.end();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 pos = getProjectAt(screenX, screenY);
		screenX = (int) pos.x;
		screenY = (int) pos.y;
		Gdx.app.log(TAG, "touch down [" + screenX +", " + screenY + "]");
		
		if(backImg.getBoundingRectangle().contains(screenX, screenY)){
			game.setScreen(screenList.get(lastScreen));
			lastScreen = NIGHTGAMESCREEN;
		}
		
		if(upArrowImg.getBoundingRectangle().contains(screenX, screenY)){
			player.moveUp();
		}
		
		if(downArrowImg.getBoundingRectangle().contains(screenX, screenY)){
			player.moveDown();
		}
		
		if(rightArrowImg.getBoundingRectangle().contains(screenX, screenY)){
			player.moveRight();
		}
		
		if(leftArrowImg.getBoundingRectangle().contains(screenX, screenY)){
			player.moveLeft();
		}
		
		return true;
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
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
        super.hide();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
