package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game.Zurvival;
import com.mygdx.game.Particles.BulletDentParticle;

public class MainMenuScreen extends BaseScreen{
	
    final String TAG = MainMenuScreen.class.getSimpleName();
	Array<BulletDentParticle> pList;
	
	//Images
	Sprite titleImg;
	Sprite newgameImg;
	Sprite continueImg;
	Sprite optionsImg;
	Sprite statImg;
	
	//Sounds
	Sound srsSound;//Single Rifle Shot
	
	public MainMenuScreen(final Zurvival gam){
		super(gam);
		pList = new Array<>();
		
		titleImg = new Sprite(new Texture("Images/Menus/Title.png"));
		newgameImg = new Sprite(new Texture("Images/Menus/New Game.png"));
		continueImg = new Sprite(new Texture("Images/Menus/Continue.png"));
		optionsImg = new Sprite(new Texture("Images/Menus/Options.png"));
		statImg = new Sprite(new Texture("Images/Menus/Statistics.png"));
		
		titleImg.scale(.2f);
		titleImg.setPosition(game.GAME_WORLD_WIDTH/2 - titleImg.getWidth()/2, game.GAME_WORLD_HEIGHT*3/4 - titleImg.getHeight()/2);
		newgameImg.setPosition(game.GAME_WORLD_WIDTH/2 - newgameImg.getWidth()/2, game.GAME_WORLD_HEIGHT/2 - newgameImg.getHeight()/2 - 50);
		continueImg.setPosition(game.GAME_WORLD_WIDTH/2 - continueImg.getWidth()/2, game.GAME_WORLD_HEIGHT/2 - continueImg.getHeight()/2 - 50);
		optionsImg.setPosition(game.GAME_WORLD_WIDTH/2 - optionsImg.getWidth()/2, game.GAME_WORLD_HEIGHT/2 - optionsImg.getHeight()/2 - 200);
		statImg.setPosition(game.GAME_WORLD_WIDTH/2 - statImg.getWidth()/2, game.GAME_WORLD_HEIGHT/2 - statImg.getHeight()/2 - 350);
		
		srsSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Single Rifle Shot.mp3"));
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		for(int i = 0; i < pList.size; i++){
			pList.get(i).update(delta);
			if(!pList.get(i).isAlive){
				pList.removeIndex(i);
			}
		}
		
		game.batch.begin();
		titleImg.draw(game.batch);
		newgameImg.draw(game.batch);
		//continueImg.draw(game.batch);
		optionsImg.draw(game.batch);
		statImg.draw(game.batch);
		
		for(BulletDentParticle p : pList){
			p.img.draw(game.batch);
			//game.batch.draw(p.img, p.x - p.img.getWidth()/2, p.y - p.img.getHeight()/2);
		}
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
		pList.clear();
		super.hide();
	}

	@Override
	public void dispose() {
		titleImg.getTexture().dispose();
		newgameImg.getTexture().dispose();
		continueImg.getTexture().dispose();
		optionsImg.getTexture().dispose();
		statImg.getTexture().dispose();
		srsSound.dispose();
	}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	Vector3 pos = getProjectAt(screenX, screenY);
    	screenX = (int) pos.x;
        screenY = (int) pos.y;
    	Gdx.app.log(TAG, "touch down [" + screenX + ", " + screenY + "]");

        fireBullet(screenX, screenY);

        //New Game
        if (newgameImg.getBoundingRectangle().contains(screenX, screenY)) {
            game.lastScreen = game.MAINMENUSCREEN;
            game.setScreen(game.screenList.get(game.NIGHTGAMESCREEN));
        }

        //Continue
        if (continueImg.getBoundingRectangle().contains(screenX, screenY)) {
            game.lastScreen = game.MAINMENUSCREEN;

        }

        //Options
        if (optionsImg.getBoundingRectangle().contains(screenX, screenY)) {
        	game.lastScreen = game.MAINMENUSCREEN;
            game.setScreen(game.screenList.get(game.OPTIONSSCREEN));

        }

        //Statistics
        if (statImg.getBoundingRectangle().contains(screenX, screenY)) {
        	game.lastScreen = game.MAINMENUSCREEN;
            game.setScreen(game.screenList.get(game.STATISTICSSCREEN));

        }
        return true;
    }

    public boolean touchDragged (int x, int y, int pointer) {
    	Vector3 pos = getProjectAt(x, y);
        fireBullet(pos.x, pos.y);
        return true;
    }



    @Override
    public boolean keyDown(int keyCode){
        Gdx.app.log(TAG, "key down: " + Keys.toString(keyCode));

        if(keyCode == Keys.R){
            game.showRobinFunsiesScreen();
        }
        return true;
    }

    private void fireBullet(float x, float y){
        if(this.time >= this.lastTouchedTime + this.delay) {
        	this.delay = DEFAULTDELAY;
        	this.lastTouchedTime = System.currentTimeMillis();
            pList.add(new BulletDentParticle(x, y));
            srsSound.play(game.volume);
        }
    }
}
