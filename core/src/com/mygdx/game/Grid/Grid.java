package com.mygdx.game.Grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.Game.Zurvival.GRID_START_X;

public class Grid {
	int rows;
	int cols;
	
	Color gridColor;
	
	Array<Array<Tile>> tilemap;
	
	ShapeRenderer sr;
	
	public Grid(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		
		this.sr = new ShapeRenderer();
		
		this.gridColor = Color.WHITE;
		
		this.tilemap = new Array<Array<Tile>>();
		for(int r = 0; r < this.rows; r++){
			Array<Tile> tmp = new Array<>();
			for(int c = 0; c < this.cols; c++){
				tmp.add(new Tile(c,r));
			}
			this.tilemap.add(tmp);
		}
	}
	
	public void drawGridLines(){
		this.sr.begin(ShapeRenderer.ShapeType.Line);
		for(Array<Tile> tmp : this.tilemap){
			for(Tile t : tmp){
				if(t.o != null && t.o.toString().equals("Player")){
					this.sr.setColor(Color.BLUE);
				}else if(t.x == GRID_START_X){
					this.sr.setColor(Color.RED);
				}else{
					this.sr.setColor(this.gridColor);	
				}
				this.sr.line(t.x, t.y, t.x, t.y-t.height);//Left line
				this.sr.line(t.x, t.y, t.x+t.width, t.y);//Top line
				this.sr.line(t.x+t.width, t.y, t.x+t.width, t.y-t.height);//Right line
				this.sr.line(t.x, t.y-t.height, t.x+t.width, t.y-t.height);//Bottom Line
			}
		}
		this.sr.end();
	}
	
	public Tile getTile(int col, int row){
		return this.tilemap.get(row).get(col);
	}
	
	public int getCols(){
		return this.cols;
	}
	
	public int getRows(){
		return this.rows;
	}
}
