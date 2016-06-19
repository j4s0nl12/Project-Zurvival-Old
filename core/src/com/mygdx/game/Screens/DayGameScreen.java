package com.mygdx.game.Screens;

import com.mygdx.game.Game.Zurvival;

public class DayGameScreen extends BaseScreen{
	final static String TAG = DayGameScreen.class.getSimpleName();
	
	public DayGameScreen(final Zurvival gam){
		super(gam);
	}
	
	public void init(){
		
		super.init();
	}
	
	@Override
	public void show() {
		if(!this.isInit)
			this.init();
		super.show();
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
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
