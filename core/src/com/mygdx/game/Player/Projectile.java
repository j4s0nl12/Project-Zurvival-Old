package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.Game.Zurvival.GAME_WORLD_WIDTH;
import static com.mygdx.game.Game.Zurvival.GAME_WORLD_HEIGHT;

public class Projectile {
	protected Vector2 pos;
	protected Vector2 vel;
	protected Vector2 dest;
	protected Vector2 gravity;
	protected float spd;
	
	public int pierceCount;
	public int damage;
	
	public boolean tobeDestroyed;
	
	public Sprite img;
	
	public ParticleEffect p;
	
	public Projectile(float x, float y, float dirX, float dirY){//, int lane){
		this.pos = new Vector2(x,y);
		this.dest = new Vector2(dirX,dirY);
		this.vel = this.dest.sub(this.pos).nor();
		
		this.p = new ParticleEffect();
		this.p.load(Gdx.files.internal("Images/Particles/BulletFire.particle"), Gdx.files.internal("Images/Particles/"));
		this.p.getEmitters().first().setPosition(this.pos.x, this.pos.y);
		this.p.start();
		
		this.pierceCount = 0;
		this.damage = 100;
		
		this.tobeDestroyed = false;
	}
	
	public void update(float delta){
		if(this.img != null){
			float tmpX = this.img.getX() + (this.vel.x * this.spd * delta);
			float tmpY = this.img.getY() + (this.vel.y * this.spd * delta);
			this.img.setPosition(tmpX, tmpY);
			this.p.getEmitters().first().setPosition(tmpX, tmpY);
			this.p.update(delta);
			
			if(this.gravity != null){
				Vector2 tmp = this.gravity.scl(delta);
				this.vel.sub(tmp);
			}
			
			if(!this.img.getBoundingRectangle().overlaps(new Rectangle(0,0,GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT))){
				this.tobeDestroyed = true;
			}
			
			
		}
	}
	
	public void setImageandBounds(String img){
		this.img = new Sprite(new Texture(img));
		this.img.setOrigin(this.img.getWidth()/2, this.img.getHeight()/2);
		this.img.setPosition(this.pos.x, this.pos.y);
	}
}
