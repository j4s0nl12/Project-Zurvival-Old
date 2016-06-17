package com.mygdx.game.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Screens.DayGameScreen;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.NightGameScreen;
import com.mygdx.game.Screens.OptionsScreen;
import com.mygdx.game.Screens.PrepareGameScreen;
import com.mygdx.game.robin.RobinFunsiesScreen;
import com.mygdx.game.Screens.StatisticsScreen;

public class Zurvival extends Game {
	public SpriteBatch batch;
	public static int lastScreen;
	public static Array<Screen> screenList;
	
	public static final int MAINMENUSCREEN = 0;
	public static final int DAYGAMESCREEN = 1;
	public static final int PREPAREGAMESCREEN = 2;
	public static final int NIGHTGAMESCREEN = 3;
	public static final int OPTIONSSCREEN = 4;
	public static final int STATISTICSSCREEN = 5;

	public static final int GAME_WORLD_WIDTH = 1600;
	public static final int GAME_WORLD_HEIGHT = 1000;

	public static OrthographicCamera camera;
	public static Viewport viewport;
	public static float aspectRatio;
	
	public static float volume;
	
    public static int width;//Start referring to GAME_WORLD_WIDTH instead
    public static int height;//Start referring to GAME_WORLD_HEIGHT instead
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		screenList = new Array<>();
		
		camera = new OrthographicCamera();
		aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
		//viewport = new StretchViewport(GAME_WORLD_WIDTH * aspectRatio, GAME_WORLD_HEIGHT, camera);
		viewport = new ExtendViewport(GAME_WORLD_WIDTH * aspectRatio, GAME_WORLD_HEIGHT, camera);
		viewport.apply();
		camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, 0);
		
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
		
		//init screens
		screenList.add(new MainMenuScreen(this));
		screenList.add(new DayGameScreen(this));
		screenList.add(new PrepareGameScreen(this));
		screenList.add(new NightGameScreen(this));
		screenList.add(new OptionsScreen(this));
		screenList.add(new StatisticsScreen(this));
		
		volume = 0.02f;
		
		lastScreen = MAINMENUSCREEN;
		this.setScreen(screenList.get(MAINMENUSCREEN));
	}
	
	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
		camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, 0);
	}

	@Override
	public void render() {
		super.render();
	}
	
	public void dispose(){
		batch.dispose();
	}

    public void showRobinFunsiesScreen() {
        setScreen(new RobinFunsiesScreen(this));
    }
}
