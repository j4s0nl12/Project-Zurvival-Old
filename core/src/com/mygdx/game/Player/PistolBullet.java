package com.mygdx.game.Player;

public class PistolBullet extends Projectile{

	public PistolBullet(float x, float y, float dirX, float dirY) {
		super(x, y, dirX, dirY);
		super.setImageandBounds("Sprites/orange.png");
		this.spd = 150;
	}
	
	public void update(float delta){
		super.update(delta);
	}

}
