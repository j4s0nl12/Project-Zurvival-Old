package com.mygdx.game.Screens;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game.Zurvival;
import com.mygdx.game.Grid.Grid;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.Projectile;
import com.mygdx.game.Zombies.BaseZombie;

public class NightGameScreen extends BaseScreen{
	
	final String TAG = NightGameScreen.class.getSimpleName();
	
	Grid grid;
	Player player;
	
	Array<BaseZombie> zList;
	long zombieSpawnTimer;
	
	//Images
	Sprite backImg;
	Sprite upArrowImg;
	Sprite downArrowImg;
	Sprite rightArrowImg;
	Sprite leftArrowImg;
	
	public NightGameScreen(final Zurvival gam){
		super(gam);
	}
	
	public void init(){
		backImg = new Sprite(new Texture("Images/Menus/Back.png"));
		upArrowImg = new Sprite(new Texture("Sprites/Up Arrow.png"));
		downArrowImg = new Sprite(new Texture("Sprites/Down Arrow.png"));
		rightArrowImg = new Sprite(new Texture("Sprites/Right Arrow.png"));
		leftArrowImg = new Sprite(new Texture("Sprites/Left Arrow.png"));

		int centerX = 125;
		int centerY = 125;
		float div = 1.25f;
		
		backImg.setPosition(game.GAME_WORLD_WIDTH*8/9 - backImg.getWidth()/2, game.GAME_WORLD_HEIGHT*8/9 - backImg.getHeight()/2);
		upArrowImg.setPosition(centerX, centerY + upArrowImg.getHeight()/div);
		downArrowImg.setPosition(centerX, centerY - downArrowImg.getHeight()/div);
		rightArrowImg.setPosition(centerX + rightArrowImg.getWidth()/div, centerY);
		leftArrowImg.setPosition(centerX - leftArrowImg.getWidth()/div, centerY);
		
		grid = new Grid(game.GRID_ROWS, game.GRID_COLS);
		
		zList = new Array<>();
		zList.add(new BaseZombie(0));
		zList.add(new BaseZombie(1));
		zList.add(new BaseZombie(2));
		zList.add(new BaseZombie(3));
		zList.add(new BaseZombie(4));
		
		
		player = new Player(grid.getTile(0, 2), grid);
		super.init();
	}
	
	@Override
	public void show() {
		if(!this.isInit || zList.size == 0)
			this.init();
		super.show();
		player.setFireRate(300L);
		player.setLastFiredTime(lastTouchedTime);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		grid.drawGridLines();
		for(BaseZombie z : zList){
			z.update(delta);
		}
		
		player.update(delta);
		
		Iterator<Projectile> bIter = player.bList.iterator();
		while(bIter.hasNext()){
			Projectile b = bIter.next();
			Iterator<BaseZombie> zIter = zList.iterator();
			while(zIter.hasNext()){
				BaseZombie z = zIter.next();
				if(b.img.getBoundingRectangle().overlaps(z.img.getBoundingRectangle())){
					z.hp -= b.damage;
					b.pierceCount -= 1;
					if(b.pierceCount < 0){
						bIter.remove();
					}
					if(z.hp <= 0){
						zIter.remove();
						break;
					}
				}
			}
		}
		
		game.batch.begin();
		player.img.draw(game.batch);
		for(BaseZombie z : zList){
			z.img.draw(game.batch);
		}
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
			game.setScreen(game.screenList.get(game.lastScreen));
			game.lastScreen = game.NIGHTGAMESCREEN;
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
