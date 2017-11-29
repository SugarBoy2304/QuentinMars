package inc.tortuga.sugarboy.quentinmars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.logic.GameConfig;

public class DesktopLauncherSmallWindow {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// 480x720
		// 640x960
		// 1080х1920

		config.title = "Quentin Mars";
		config.width = 640;
		config.height = 360;
		new LwjglApplication(new Game(new GameConfig(config.width, config.height, config.title, "PC")), config);
	}
}
