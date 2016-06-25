package com.mygdx.game.Player;

public class PistolBullet extends Projectile{

	public PistolBullet(float x, float y, float dirX, float dirY) {
		super(x, y, dirX, dirY);
		super.setImageandBounds("Sprites/BulletTmp.png");
		this.img.setOriginCenter();
		System.out.println(this.angle);
		this.img.rotate(this.angle);
		this.spd = 1500;
	}
	
	public void update(float delta){
		super.update(delta);
	}

}
