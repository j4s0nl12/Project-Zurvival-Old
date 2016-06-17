package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.Game.Zurvival;

import static com.mygdx.game.Game.Zurvival.screenList;
import static com.mygdx.game.Game.Zurvival.lastScreen;
import static com.mygdx.game.Game.Zurvival.OPTIONSSCREEN;
import static com.mygdx.game.Game.Zurvival.viewport;

public class OptionsScreen extends BaseScreen{
	final static String TAG = OptionsScreen.class.getSimpleName();
	
	Sprite backImg;
	
	TextureAtlas atlas;
	Skin skin;
	Slider vol;
	Stage stage;
	
	public OptionsScreen(final Zurvival gam){
		super(gam);
		
		backImg = new Sprite(new Texture("Images/Menus/Back.png"));
		backImg.setPosition(game.GAME_WORLD_WIDTH/2 - backImg.getWidth()/2, game.GAME_WORLD_HEIGHT/4 - backImg.getHeight()/2);

		stage = new Stage(viewport);

		atlas = new TextureAtlas("data/uiskin.atlas");
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		skin.addRegions(atlas);

		vol = new Slider(0, 1, 0.01f, false, skin);
		vol.setValue(game.getPref().getFloat("volume"));
		vol.setWidth(game.GAME_WORLD_WIDTH/2);
		vol.setPosition(game.GAME_WORLD_WIDTH/2, game.GAME_WORLD_HEIGHT/2 - vol.getHeight()/2, 0);
		vol.setAnimateDuration(0.3f);
		vol.addListener(new ChangeListener(){
			public void changed (ChangeEvent event, Actor actor){
				Gdx.app.log("UITest",  "slider: " + vol.getValue());
				game.volume = vol.getValue();
				game.getPref().putFloat("volume", vol.getValue());
				game.getPref().flush();
			}
		});
		stage.addActor(vol);
	}
	
	@Override
	public void show() {
		super.show();
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		stage.act(delta);
		stage.draw();
		
		game.batch.begin();
		backImg.draw(game.batch);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		stage.getViewport().update(width, height, true);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		
	}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 pos = getProjectAt(screenX, screenY);
    	screenX = (int) pos.x;
        screenY = (int) pos.y;
        Gdx.app.log(TAG, "touch down [" + screenX +", " + screenY + "]");

        if(backImg.getBoundingRectangle().contains(screenX, screenY)){
            game.setScreen(screenList.get(lastScreen));
            lastScreen = OPTIONSSCREEN;
        }
        return true;
    }
}
