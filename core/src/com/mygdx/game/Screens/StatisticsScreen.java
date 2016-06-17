package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Game.Zurvival;

import static com.mygdx.game.Game.Zurvival.screenList;
import static com.mygdx.game.Game.Zurvival.lastScreen;
import static com.mygdx.game.Game.Zurvival.STATISTICSSCREEN;

public class StatisticsScreen extends BaseScreen{
	final static String TAG = StatisticsScreen.class.getSimpleName();
	
	Sprite backImg;
	
	public StatisticsScreen(final Zurvival gam){
		super(gam);
		
		backImg = new Sprite(new Texture("Images/Menus/Back.png"));
		backImg.setPosition(game.GAME_WORLD_WIDTH/2 - backImg.getWidth()/2, game.GAME_WORLD_HEIGHT/2 - backImg.getHeight()/2);
	}
	
	@Override
	public void show() {
		super.show();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		game.batch.begin();
		backImg.draw(game.batch);
		game.batch.end();
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

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 pos = getProjectAt(screenX, screenY);
		screenX = (int) pos.x;
		screenY = (int) pos.y;
		Gdx.app.log(TAG, "touch down [" + screenX +", " + screenY + "]");

		if(backImg.getBoundingRectangle().contains(screenX, screenY)){
			game.setScreen(screenList.get(lastScreen));
			lastScreen = STATISTICSSCREEN;
		}
		return true;
	}
}
