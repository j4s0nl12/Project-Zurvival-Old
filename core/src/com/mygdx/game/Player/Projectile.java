package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
	protected Vector2 pos;
	protected Vector2 vel;
	protected Vector2 dest;
	protected Vector2 gravity;
	protected float spd;
	
	public boolean tobeDestroyed;
	
	public Texture img;
	
	public Rectangle bounds;
	
	public Projectile(float x, float y, float dirX, float dirY){
		this.pos = new Vector2(x,y);
		this.dest = new Vector2(dirX,dirY);
		this.vel = this.dest.sub(this.pos).nor();
		
		this.tobeDestroyed = false;
	}
	
	public void update(float delta){
		if(this.bounds != null){
			this.bounds.x += this.vel.x * this.spd * delta;
			this.bounds.y += this.vel.y * this.spd * delta;
			
			if(this.gravity != null){
				Vector2 tmp = this.gravity.scl(delta);
				this.vel.sub(tmp);
			}
			
			if(!this.bounds.overlaps(new Rectangle(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()))){
				this.tobeDestroyed = true;
			}
		}
	}
	
	public void setImageandBounds(String img){
		this.img = new Texture(img);
		this.bounds = new Rectangle(this.pos.x, this.pos.y, this.img.getWidth(), this.img.getHeight());
	}
}
