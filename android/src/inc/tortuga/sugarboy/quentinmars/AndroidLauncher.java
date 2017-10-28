package inc.tortuga.sugarboy.quentinmars;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import inc.tortuga.sugarboy.quentinmars.utils.logic.GameConfig;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Game(new GameConfig(720, 1280, "Quentin Mars", "Android")), config);
	}
}
