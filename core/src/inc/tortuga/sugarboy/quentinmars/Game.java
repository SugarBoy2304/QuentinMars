package inc.tortuga.sugarboy.quentinmars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inc.tortuga.sugarboy.quentinmars.screens.menu.MainMenuScreen;
import inc.tortuga.sugarboy.quentinmars.utils.logic.GameConfig;
import inc.tortuga.sugarboy.quentinmars.utils.visual.Fonts;
import inc.tortuga.sugarboy.quentinmars.utils.visual.StateManager;

public class Game extends ApplicationAdapter {

	private static Game instance;

	private GameConfig config;
	private Fonts fonts;
	private SpriteBatch batch;
	private StateManager stateManager;

	public Game(GameConfig config) {
		instance = this;
		this.config = config;
	}

	public static Game get() {
		return instance;
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	public GameConfig getConfig() {
		return this.config;
	}

	public Fonts getFonts() {
		return this.fonts;
	}

	@Override
	public void create () {
		config.load();
		batch = new SpriteBatch();
		fonts = new Fonts();
		stateManager = new StateManager();
		stateManager.push(new MainMenuScreen(stateManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
