package com.mygdx.game.Particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BulletDentParticle {
	
	private long ttl;//Time to live in milliseconds
	public Texture img;
	public float x;
	public float y;
	private long spawnedTime;
	private long time;
	public boolean isAlive;
	
	public BulletDentParticle(float x, float y){
		this.x = x;
		this.y = y;
		this.img = new Texture("Images/Particles/BulletDent.png");
		this.ttl = 1000;
		this.spawnedTime = System.currentTimeMillis();
		this.time = spawnedTime;
		this.isAlive = true;
	}
	
	public void update(float delta){
		this.time = System.currentTimeMillis();
		if(this.time >= this.spawnedTime + this.ttl){
			this.isAlive = false;
		}
	}
}
