package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Grid.Grid;
import com.mygdx.game.Grid.Tile;

import static com.mygdx.game.Game.Zurvival.volume;

public class Player {
	private float x;
	private float y;
	
	public Tile tile;
	public Grid grid;
	
	public Sprite img;
	
	public Array<Projectile> bList;
	private String currentGun;
	private long fireRate;
	
	private long time;
	private long lastFiredTime;
	
	private Rectangle shootRect;
	
	private Sound srsSound;//Single Rifle Shot
	
	public Player(Tile t, Grid grid){
		this.tile = t;
		this.grid = grid;
		this.tile.setObject(this);

		this.x = this.tile.getX() - this.tile.getWidth()/4;
		this.y = this.tile.getY() - this.tile.getHeight();
		this.img = new Sprite(new Texture("Sprites/Temp Player.png"));
		this.img.setPosition(this.x, this.y);
		//this.img.setPosition(GAME_WORLD_WIDTH/2 - this.img.getWidth()/2, GAME_WORLD_HEIGHT/2 - this.img.getHeight()/2);
		
		Tile rectOrigin = this.grid.getTile(1, grid.getRows()-1);
		this.shootRect = new Rectangle(rectOrigin.getX(), rectOrigin.getY()-rectOrigin.getHeight(), rectOrigin.getWidth() * grid.getCols()-2, rectOrigin.getHeight() * grid.getRows());
		
		this.bList = new Array<>();
		
		this.currentGun = "Pistol";
		this.time = System.currentTimeMillis();
		this.lastFiredTime = time + 1000L;
		this.fireRate = 250L;
		this.srsSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Single Rifle Shot.mp3"));
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
			float tmpX = Gdx.input.getX();
			float tmpY = Gdx.graphics.getHeight() - Gdx.input.getY();
			if(time >= lastFiredTime + fireRate && this.shootRect.contains(tmpX, tmpY)){
				this.fireRate = getFireRate(this.currentGun);
				this.fireBullet(tmpX, tmpY);
			}
		}
	}
	
	public void moveUp(){
		if(this.tile.getRow() >= 1){
			this.tile.setObject(null);
			this.tile = this.grid.getTile(this.tile.getCol(), this.tile.getRow() - 1);
			this.tile.setObject(this);
			this.y = this.tile.getY() - this.tile.getHeight();
			this.img.setPosition(this.x, this.y);
		}	
	}
	
	public void moveDown(){
		if(this.tile.getRow() < grid.getRows() - 1){
			this.tile.setObject(null);
			this.tile = this.grid.getTile(this.tile.getCol(), this.tile.getRow() + 1);
			this.tile.setObject(this);
			this.y = this.tile.getY() - this.tile.getHeight();
			this.img.setPosition(this.x, this.y);
		}
	}
	
	public void moveRight(){
		if(this.tile.getCol() < grid.getCols() - 1){
			this.tile.setObject(null);
			this.tile = this.grid.getTile(this.tile.getCol() + 1, this.tile.getRow());
			this.tile.setObject(this);
			this.x = this.tile.getX() - this.tile.getWidth()/4;
			this.img.setPosition(this.x, this.y);
		}
	}
	
	public void moveLeft(){
		if(this.tile.getCol() >= 1){
			this.tile.setObject(null);
			this.tile = this.grid.getTile(this.tile.getCol() - 1, this.tile.getRow());
			this.tile.setObject(this);
			this.x = this.tile.getX() - this.tile.getWidth()/4;
			this.img.setPosition(this.x, this.y);
		}
	}
	
	public void fireBullet(float x, float y){
		switch(this.currentGun){
			default:
				this.srsSound.play(volume);		
		}
		this.lastFiredTime = System.currentTimeMillis();
		this.bList.add(new PistolBullet(this.img.getX() + this.img.getWidth()/2, this.img.getY() + this.img.getHeight()/2, x, y));
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public void setLastFiredTime(long time){
		this.lastFiredTime = time;
	}
	
	public void setFireRate(long time){
		this.fireRate = time;
	}
	
	public String toString(){
		return "Player";
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
