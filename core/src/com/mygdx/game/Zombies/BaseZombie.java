package com.mygdx.game.Zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BaseZombie {
	private float x;
	public int lane;
	
	Sprite img;
	
	public BaseZombie(float x, int lane){
		this.x = x;
		this.lane = lane;
		
		//img = new Sprite(new Texture(""));
	}
	
	public void update(float delta){
		
	}
}
