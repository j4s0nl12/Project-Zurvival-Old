package com.mygdx.game.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
	
	private static Preferences pref;
	
	public static final int MAINMENUSCREEN = 0;
	public static final int DAYGAMESCREEN = 1;
	public static final int PREPAREGAMESCREEN = 2;
	public static final int NIGHTGAMESCREEN = 3;
	public static final int OPTIONSSCREEN = 4;
	public static final int STATISTICSSCREEN = 5;

	public static final int GAME_WORLD_WIDTH = 1600;
	public static final int GAME_WORLD_HEIGHT = 1000;

	//Will change this later, meh
	public static final int GRID_ROWS = 5;
	public static final int GRID_COLS = 10;
	public static final int GRID_WIDTH = 1330;
	public static final int GRID_HEIGHT = 780;
	public static final int GRID_START_X = GAME_WORLD_WIDTH - GRID_WIDTH;//Top left origin of grid
	public static final int GRID_START_Y = GRID_HEIGHT;
	public static final int TILE_WIDTH = GRID_WIDTH/GRID_COLS;
	public static final int TILE_HEIGHT = GRID_START_Y/GRID_ROWS;

	private static final String PREF_NAME = "mygame_pref";
	
	public static OrthographicCamera camera;
	public static Viewport viewport;
	
	public static float volume;

	@Override
	public void create() {
		batch = new SpriteBatch();
		screenList = new Array<>();
		
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera);
		viewport.apply();
		camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, 0);

		//Pref test
		if(pref == null)
			pref = Gdx.app.getPreferences(PREF_NAME);
		
		if(!pref.contains("volume")){
			volume = 0.02f;
			pref.putFloat("volume", volume);
			pref.flush();
		}else{
			volume = pref.getFloat("volume");
		}

		/*
		    init screens
			Needs to be almost the last thing
			to be called since it depends on initial variables above.
		 */
		screenList.add(new MainMenuScreen(this));
		screenList.add(new DayGameScreen(this));
		screenList.add(new PrepareGameScreen(this));
		screenList.add(new NightGameScreen(this));
		screenList.add(new OptionsScreen(this));
		screenList.add(new StatisticsScreen(this));
		
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
    
    public Preferences getPref(){
    	return pref;
    }
}
