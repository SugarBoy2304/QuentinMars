package inc.tortuga.sugarboy.quentinmars;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import inc.tortuga.sugarboy.quentinmars.utils.logic.GameConfig;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useCompass = false;
		config.useAccelerometer = false;
		initialize(new Game(new GameConfig(0, 0, "Quentin Mars", "Android")), config);
	}
}
