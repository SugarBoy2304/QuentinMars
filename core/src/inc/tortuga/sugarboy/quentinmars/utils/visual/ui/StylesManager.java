package inc.tortuga.sugarboy.quentinmars.utils.visual.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.Arrays;
import java.util.Stack;

import inc.tortuga.sugarboy.quentinmars.Game;

/**
 * Created by swift on 31.10.2017.
 */

public class StylesManager {

    private final String FONT_CHARS = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrs" +
            "tuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXY" +
            "Z0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    private final float k = Game.get().getConfig().k;

    private final ArrayMap<String, BitmapFont> FONTS;
    private final ArrayMap<String, TextButton.TextButtonStyle> BUTTONS;
    private final ArrayMap<String, GameDivStyle> DIVS;

    private TextureAtlas atlas;
    private Skin skin;

    public StylesManager() {
        FONTS = new ArrayMap<String, BitmapFont>();
        BUTTONS = new ArrayMap<String, TextButton.TextButtonStyle>();
        DIVS = new ArrayMap<String, GameDivStyle>();
    }

    public BitmapFont loadFont(String key) {

        if (FONTS.containsKey(key)) return FONTS.get(key);

        FreeTypeFontGenerator font;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;

        if (key.equalsIgnoreCase("main")) {
            font = new FreeTypeFontGenerator(Gdx.files.internal("fonts/hacked.ttf"));

            parameter.size = (int) (40 * k);
            parameter.color = Color.WHITE;
            parameter.shadowOffsetX = (int) (2 * k);
            parameter.shadowOffsetY = (int) (2 * k);

        } else if (key.equalsIgnoreCase("main_big")) {
            font = new FreeTypeFontGenerator(Gdx.files.internal("fonts/hacked.ttf"));

            parameter.shadowOffsetX = (int) (7 * k);
            parameter.shadowOffsetY = (int) (5 * k);
            parameter.size = (int) (80 * k);
            parameter.color = Color.WHITE;

        } else if (key.equalsIgnoreCase("orbitron")) {
            font = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Orbitron.ttf"));

            parameter.shadowOffsetX = 1;
            parameter.shadowOffsetY = 1;
            parameter.shadowColor = Color.DARK_GRAY;
            parameter.size = (int) (32 * k);
            parameter.color = Color.LIGHT_GRAY;

        } else return null;

        FONTS.put(key, font.generateFont(parameter));
        font.dispose();
        return FONTS.peekValue();
    }


    public TextButton.TextButtonStyle loadButton(String key) {

        if (BUTTONS.containsKey(key)) return BUTTONS.get(key);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        if (key.equalsIgnoreCase("default")) {
            style.up = skin.getDrawable("button.up");
            style.down = skin.getDrawable("button.down");
            style.pressedOffsetX = 1F;
            style.pressedOffsetY = -1F;
            style.font = Game.get().getFonts().main;
            style.fontColor = new Color(1F, 1F, 1F, 1);
            style.downFontColor = new Color(0.35F, 0.91F, 0.63F, 1F);
        } else return null;

        BUTTONS.put(key, style);
        return BUTTONS.peekValue();
    }

    public Skin skin() {
        if (atlas == null) {
            atlas = new TextureAtlas("ui/gui.atlas");
            skin = new Skin(atlas);
        }
        return skin;
    }

    public void dispose() {
        this.disposeScreen();
        atlas.dispose();
        skin.dispose();
    }

    public void disposeScreen() {
        for (BitmapFont font : FONTS.values()) font.dispose();
        for (GameDivStyle style : DIVS.values()) style.dispose();
    }



}
