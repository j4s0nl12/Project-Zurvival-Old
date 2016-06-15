package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Player {
	private int x;
	private int y;
	
	public Texture img;
	
	private boolean beingTouched;
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		
		this.img = new Texture("Sprites/Temp Player.png");
		beingTouched = false;
	}
	
	public void update(float delta){
		//Up
		if((Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP))){
			moveUp();
		}
		
		//Down
		if((Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN))){
			moveDown();
		}
		
		//Right
		if((Gdx.input.isKeyJustPressed(Keys.D) || Gdx.input.isKeyJustPressed(Keys.RIGHT))){
			moveRight();
		}
		
		//Left
		if((Gdx.input.isKeyJustPressed(Keys.A) || Gdx.input.isKeyJustPressed(Keys.LEFT))){
			moveLeft();
		}
		
		if(Gdx.input.isTouched() && !beingTouched){
			beingTouched = true;
		}
		
		if(!Gdx.input.isTouched()){
			beingTouched = false;
		}
	}
	
	public void render(float delta){
		
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
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
}
