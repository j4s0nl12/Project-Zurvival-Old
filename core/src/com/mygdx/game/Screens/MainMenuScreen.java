package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game.Zurvival;
import com.mygdx.game.Particles.BulletDentParticle;

import static com.mygdx.game.Game.Zurvival.screenList;
import static com.mygdx.game.Game.Zurvival.lastScreen;
import static com.mygdx.game.Game.Zurvival.MAINMENUSCREEN;
import static com.mygdx.game.Game.Zurvival.DAYGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.PREPAREGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.NIGHTGAMESCREEN;
import static com.mygdx.game.Game.Zurvival.OPTIONSSCREEN;
import static com.mygdx.game.Game.Zurvival.STATISTICSSCREEN;


public class MainMenuScreen extends InputAdapter implements Screen{
	
	final Zurvival game;
    final static String TAG = MainMenuScreen.class.getSimpleName();
	OrthographicCamera camera;
	Array<BulletDentParticle> pList;
	private long time;
	private long lastTouchedTime;
	public long delay;
	
	//Images
	Texture titleImg;
	Texture newgameImg;
	Texture continueImg;
	Texture optionsImg;
	Texture statImg;
	
	//Image bounds
	Rectangle newgameBound;
	Rectangle continueBound;
	Rectangle optionsBound;
	Rectangle statBound;
	
	//Sounds
	Sound srsSound;//Single Rifle Shot
    float volume = 0.02f;
	
	public MainMenuScreen(final Zurvival gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());//Look at Desktop Launcher for Desktop Resolution
		pList = new Array<>();
		
		titleImg = new Texture("Images/Menus/Title.png");
		newgameImg = new Texture("Images/Menus/New Game.png");
		continueImg = new Texture("Images/Menus/Continue.png");
		optionsImg = new Texture("Images/Menus/Options.png");
		statImg = new Texture("Images/Menus/Statistics.png");
		
		newgameBound = new Rectangle(Gdx.graphics.getWidth()/2 - newgameImg.getWidth()/2, 300, newgameImg.getWidth(), newgameImg.getHeight());
		continueBound = new Rectangle(Gdx.graphics.getWidth()/2 - continueImg.getWidth()/2, 300, continueImg.getWidth(), continueImg.getHeight());
		optionsBound = new Rectangle(Gdx.graphics.getWidth()/2 - optionsImg.getWidth()/2, 200, optionsImg.getWidth(), optionsImg.getHeight());
		statBound = new Rectangle(Gdx.graphics.getWidth()/2 - statImg.getWidth()/2, 100, statImg.getWidth(), statImg.getHeight());
		
		srsSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Single Rifle Shot.mp3"));
		time = System.currentTimeMillis();
		lastTouchedTime = time;
		delay = 250L;
	}

	@Override
	public void show() {
        Gdx.input.setInputProcessor(this);
		delay = 250L;
		lastTouchedTime = System.currentTimeMillis();
	}

	@Override
	public void render(float delta) {
		time = System.currentTimeMillis();
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))//For simple exits
			Gdx.app.exit();
			
		Gdx.gl.glClearColor(0.15f, 0.2f, 0.15f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		for(int i = 0; i < pList.size; i++){
			pList.get(i).update(delta);
			if(!pList.get(i).isAlive){
				pList.removeIndex(i);
			}
		}
		
		game.batch.begin();
		game.batch.draw(titleImg, Gdx.graphics.getWidth()/2 - titleImg.getWidth()/2, Gdx.graphics.getHeight() - 350);
		game.batch.draw(newgameImg, newgameBound.getX(), newgameBound.getY());
		//game.batch.draw(continueImg, continueBound.getX(), 300);
		game.batch.draw(optionsImg, optionsBound.getX(), optionsBound.getY());
		game.batch.draw(statImg, statBound.getX(), statBound.getY());
		for(BulletDentParticle p : pList){
			game.batch.draw(p.img, p.x - p.img.getWidth()/2, p.y - p.img.getHeight()/2);
		}
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		pList.clear();
	}

	@Override
	public void dispose() {
		titleImg.dispose();
		newgameImg.dispose();
		continueImg.dispose();
		optionsImg.dispose();
		statImg.dispose();
		srsSound.dispose();
	}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log(TAG, "touch down");
        if(time >= lastTouchedTime + delay) {
            delay = 100L;
            lastTouchedTime = System.currentTimeMillis();
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            pList.add(new BulletDentParticle(touchPos.x, touchPos.y));
            srsSound.play(volume);

            //New Game
            if (newgameBound.contains(touchPos.x, touchPos.y)) {
                lastScreen = MAINMENUSCREEN;
                //game.setScreen(new NightGameScreen(game));
                game.setScreen(screenList.get(NIGHTGAMESCREEN));
            }

            //Continue
            if (continueBound.contains(touchPos.x, touchPos.y)) {
                lastScreen = MAINMENUSCREEN;

            }

            //Options
            if (optionsBound.contains(touchPos.x, touchPos.y)) {
                lastScreen = MAINMENUSCREEN;
                game.setScreen(new OptionsScreen(game));

            }

            //Statistics
            if (statBound.contains(touchPos.x, touchPos.y)) {
                lastScreen = MAINMENUSCREEN;
                game.setScreen(new StatisticsScreen(game));

            }
        }
        return true;
    }

    @Override
    public boolean keyDown(int keyCode){
        Gdx.app.log(TAG, "key down");

        if(keyCode == Keys.R){
            game.showRobinFunsiesScreen();
        }
        return true;
    }
}
