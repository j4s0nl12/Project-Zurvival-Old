package com.mygdx.game.Zombies;

import com.badlogic.gdx.graphics.Texture;

public class BaseZombie {
	private float x;
	public int lane;
	
	Texture img;
	
	public BaseZombie(float x, int lane){
		this.x = x;
		this.lane = lane;
		
		//img = new Texture("");
	}
	
	public void update(float delta){
		
	}
}
