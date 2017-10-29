package inc.tortuga.sugarboy.quentinmars.utils.visual.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.HashMap;
import java.util.Map;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.screens.menu.MainMenuScreen;

/**
 * Created by swift on 29.10.2017.
 */

public class GameButton {

    private static final Map<String, TextButton.TextButtonStyle> styles = new HashMap<String, TextButton.TextButtonStyle>();
    private TextureAtlas atlas;
    private Skin skin;

    private Runnable onEvent;
    private Runnable offEvent;

    private TextButton button;

    public GameButton(String text, String style) {
        this.button = new TextButton(text, load(style));
    }

    public GameButton(String text) {
        this(text, "default");
    }

    public void setPosition(float x, float y) {
        button.setPosition(Gdx.graphics.getWidth() / 2F - (button.getWidth() / 2F) + x , Gdx.graphics.getHeight() / 2F - (button.getHeight() / 2F)+ y);
    }

    public void setSize(float x, float y) {
        button.setSize(x, y);
    }

    public void draw(SpriteBatch sb) {
        button.draw(sb, 1F);
    }

    public void eventOn(Runnable onEvent) {
        this.onEvent = onEvent;
        updateListener();
    }

    public void eventOff(Runnable offEvent) {
        this.offEvent = offEvent;
        updateListener();
    }

    public TextButton a() {
        return this.button;
    }

    public void updateListener() {
        button.clearListeners();
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onEvent.run();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int b) {
                offEvent.run();
            }
        });
    }

    public TextButton.TextButtonStyle load(String key) {
        key = key.toLowerCase();
        if (styles.containsKey(key)) return styles.get(key);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        if (key.equalsIgnoreCase("default")) {
            atlas = new TextureAtlas("ui/gui.atlas");
            skin = new Skin(atlas);

            style.up = skin.getDrawable("button.up");
            style.down = skin.getDrawable("button.down");
            style.pressedOffsetX = 1F;
            style.pressedOffsetY = -1F;
            style.font = Game.get().getFonts().main;
            style.fontColor = new Color(1F, 1F, 1F, 1);
            style.downFontColor = new Color(1F, 0.46F, 0.19F, 1F);
        } else return styles.get("default");
        styles.put(key, style);
        return style;

    }

}
