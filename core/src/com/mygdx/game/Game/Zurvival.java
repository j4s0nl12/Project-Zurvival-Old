package com.mygdx.game.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Screens.DayGameScreen;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.NightGameScreen;
import com.mygdx.game.Screens.OptionsScreen;
import com.mygdx.game.Screens.PrepareGameScreen;
import com.mygdx.game.Screens.RobinFunsiesScreen;
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
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		screenList = new Array<>();
		
		//init screens
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
	public void render() {
		super.render();
	}
	
	public void dispose(){
		batch.dispose();
	}
	
	public static Screen getNewScreen(int screenID, final Zurvival gam){
		Screen s = null;
		switch(screenID){
			case 0:
				s = new MainMenuScreen(gam);
				break;
			case 1:
				s = new DayGameScreen(gam);
				break;
			case 2:
				s = new PrepareGameScreen(gam);
				break;
			case 3:
				s = new NightGameScreen(gam);
				break;
			case 4:
				s = new OptionsScreen(gam);
				break;
			case 5:
				s = new StatisticsScreen(gam);
				break;
		}
		return s;
	}

    public void showRobinFunsiesScreen() {
        setScreen(new RobinFunsiesScreen(this));
    }
}
