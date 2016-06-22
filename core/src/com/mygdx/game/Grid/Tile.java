package com.mygdx.game.Grid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.mygdx.game.Game.Zurvival.TILE_WIDTH;
import static com.mygdx.game.Game.Zurvival.GRID_START_X;
import static com.mygdx.game.Game.Zurvival.GRID_START_Y;
import static com.mygdx.game.Game.Zurvival.TILE_HEIGHT;

public class Tile {
	int col;
	int row;
	int width;
	int height;
	float x;
	float y;
	Object o;
	
	Sprite img;
	
	public Tile(int col, int row){
		this.col = col;
		this.row = row;
		this.x = GRID_START_X + (TILE_WIDTH * this.col);
		this.y = GRID_START_Y - (TILE_HEIGHT * this.row);
		this.width = TILE_WIDTH;
		this.height = TILE_HEIGHT;
		
		this.o = null;
		//this.img = new Sprite(new Texture(""));
	}
	
	public void setObject(Object o){
		this.o = o;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getWidth(){
		return this.width;
	}
}
