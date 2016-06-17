package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.Game.Zurvival.GAME_WORLD_WIDTH;
import static com.mygdx.game.Game.Zurvival.GAME_WORLD_HEIGHT;

public class Player {
	private int x;
	private int y;
	
	public Sprite img;
	
	public Array<Projectile> bList;
	private String currentGun;
	private long fireRate;
	
	private long time;
	private long lastFiredTime;
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		
		this.img = new Sprite(new Texture("Sprites/Temp Player.png"));
		this.img.setPosition(GAME_WORLD_WIDTH/2 - this.img.getWidth()/2, GAME_WORLD_HEIGHT/2 - this.img.getHeight()/2);
		
		this.bList = new Array<>();
		
		this.currentGun = "Pistol";
		this.time = System.currentTimeMillis();
		this.lastFiredTime = time + 1000L;
		this.fireRate = 250L;
	}
	
	public void update(float delta){
		this.time = System.currentTimeMillis();
		
		for(Projectile p : bList){
			p.update(delta);
		}
		
		for(int i = 0; i < this.bList.size; i++){
			this.bList.get(i).update(delta);
			if(this.bList.get(i).tobeDestroyed){
				this.bList.removeIndex(i);
			}
		}
		
		//Up
		if((Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP))){
			this.moveUp();
		}
		
		//Down
		if((Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN))){
			this.moveDown();
		}
		
		//Right
		if((Gdx.input.isKeyJustPressed(Keys.D) || Gdx.input.isKeyJustPressed(Keys.RIGHT))){
			this.moveRight();
		}
		
		//Left
		if((Gdx.input.isKeyJustPressed(Keys.A) || Gdx.input.isKeyJustPressed(Keys.LEFT))){
			this.moveLeft();
		}
		
		if(Gdx.input.isTouched()){
			if(time >= lastFiredTime + fireRate){
				this.fireRate = getFireRate(this.currentGun);
				this.fireBullet(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
			}
		}
	}
	
	public void moveUp(){
		System.out.println("Implement moveUp!");
	}
	
	public void moveDown(){
		System.out.println("Implement moveDown!");
	}
	
	public void moveRight(){
		System.out.println("Implement moveRight!");
	}
	
	public void moveLeft(){
		System.out.println("Implement moveLeft!");
	}
	
	public void fireBullet(float x, float y){
		//System.out.println("[" + x + ", " + y + "]");
		this.lastFiredTime = System.currentTimeMillis();
		this.bList.add(new PistolBullet(this.x,this.y, x, y));
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setLastFiredTime(long time){
		this.lastFiredTime = time;
	}
	
	public void setFireRate(long time){
		this.fireRate = time;
	}
	
	public long getFireRate(String weaponType){
		long fr;
		switch(weaponType){
			case "Pistol":
				fr = 250L;
				break;
			default:
				fr = 0L;
		}
		return fr;
	}
}
