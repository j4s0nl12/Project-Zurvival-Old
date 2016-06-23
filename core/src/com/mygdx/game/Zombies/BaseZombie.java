package com.mygdx.game.Zombies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.mygdx.game.Game.Zurvival.GAME_WORLD_HEIGHT;
import static com.mygdx.game.Game.Zurvival.GAME_WORLD_WIDTH;
import static com.mygdx.game.Game.Zurvival.TILE_HEIGHT;

public class BaseZombie {
	private float x;
	private float y;
	private float speed;
	
	public float hp;
	
	public int lane;
	
	public Sprite img;
	
	public BaseZombie(int lane){
		this.lane = lane;

		img = new Sprite(new Texture("Sprites/cherries.png"));
		
		this.x = GAME_WORLD_WIDTH;
		this.y = TILE_HEIGHT * this.lane + img.getHeight()/4;
		
		this.speed = 30;
		
		this.hp = 100;
		
		img.setPosition(this.x, this.y);
	}
	
	public void update(float delta){
		this.x -= this.speed * delta;
		img.setPosition(this.x, this.y);
	}
}
