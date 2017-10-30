package inc.tortuga.sugarboy.quentinmars.utils.visual;

/**
 * Created by swift on 22.10.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import inc.tortuga.sugarboy.quentinmars.Game;

public class Fonts {

    private final String FONT_CHARS = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrs" +
            "tuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXY" +
            "Z0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    private final float k = Game.get().getConfig().k;

    public BitmapFont main;
    public BitmapFont main_big;

    public BitmapFont orbitron;

    public Fonts() {
        FreeTypeFontGenerator font = new FreeTypeFontGenerator(Gdx.files.internal("fonts/hacked.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;
        parameter.size = (int) (40 * k);
        parameter.color = Color.WHITE;
        parameter.shadowOffsetX = (int) (2 * k);
        parameter.shadowOffsetY = (int) (2 * k);
        main = font.generateFont(parameter);

        parameter.shadowOffsetX = (int) (7 * k);
        parameter.shadowOffsetY = (int) (5 * k);
        parameter.size = (int) (80 * k);
        main_big = font.generateFont(parameter);

        FreeTypeFontGenerator orbit = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Orbitron.ttf"));
        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;
        parameter.shadowColor = Color.DARK_GRAY;
        parameter.size = (int) (32 * k);
        parameter.color = Color.LIGHT_GRAY;
        orbitron = orbit.generateFont(parameter);

        font.dispose();
        orbit.dispose();
    }

    public void dispose() {
        main.dispose();
        main_big.dispose();
        orbitron.dispose();
    }

}