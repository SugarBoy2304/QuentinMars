package inc.tortuga.sugarboy.quentinmars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import inc.tortuga.sugarboy.quentinmars.screens.menu.MainMenuScreen;
import inc.tortuga.sugarboy.quentinmars.utils.logic.GameConfig;
import inc.tortuga.sugarboy.quentinmars.utils.visual.Fonts;
import inc.tortuga.sugarboy.quentinmars.utils.logic.StateManager;

public class Game extends ApplicationAdapter {

	private static Game instance;

	private GameConfig config;
	private Fonts fonts;
	private SpriteBatch batch;
	private StateManager stateManager;
	public ShaderProgram shader;

	// TODO remove
	public Texture debug;

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
		batch = new SpriteBatch();

		debug = new Texture("ui/menu/debug.png");

		config.load();
		fonts = new Fonts();
		stateManager = new StateManager();
		stateManager.push(new MainMenuScreen(stateManager, 0F));

//C:\Users\swift\Desktop\LibGDX\Quentin Mars\core\src\inc\tortuga\sugarboy\quentinmars\shaders\fShader.frag
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/fShader.vert"), Gdx.files.internal("shaders/fShader.frag"));
		if (!shader.isCompiled()) {
			System.err.println(shader.getLog());
			System.exit(-2894);
		}
		batch.setShader(shader);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(batch);
	}
	
	@Override
	public void dispose () {
		System.out.println("Sooqa $");
		batch.dispose();
		debug.dispose();
		stateManager.getState().dispose();
	}
}
