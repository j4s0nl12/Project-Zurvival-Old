package com.mygdx.game.Particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BulletDentParticle {
	
	private long ttl;//Time to live in milliseconds
	public Sprite img;
	public float x;
	public float y;
	private long spawnedTime;
	private long time;
	public boolean isAlive;
	public float alpha;
	
	public BulletDentParticle(float x, float y){
		this.img = new Sprite(new Texture("Images/Particles/BulletDent.png"));
		this.img.setPosition(x - this.img.getWidth()/2, y - this.img.getHeight()/2);
		this.ttl = 1000;
		this.spawnedTime = System.currentTimeMillis();
		this.time = spawnedTime;
		this.isAlive = true;
		this.alpha = 1;
	}
	
	public void update(float delta){
		this.time = System.currentTimeMillis();
		//this.alpha = (float)((((double)(this.spawnedTime) + (double)(this.ttl)) - (double)(this.time))/(double)(this.ttl));
		if((this.spawnedTime + this.ttl - this.time) <= 50){
			this.alpha -= 2f  * delta;
			this.img.setAlpha(this.alpha);
		}
		if(this.alpha <= 0){
			this.isAlive = false;
		}
	}
}
