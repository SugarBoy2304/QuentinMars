package inc.tortuga.sugarboy.quentinmars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.logic.GameConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Quentin Mars";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new Game(new GameConfig(config.width, config.height, config.title, "PC")), config);
	}
}
