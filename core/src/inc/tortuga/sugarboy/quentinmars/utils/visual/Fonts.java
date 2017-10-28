package inc.tortuga.sugarboy.quentinmars.utils.visual;

/**
 * Created by swift on 22.10.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

public class Fonts {

    private final String FONT_CHARS = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrs" +
            "tuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXY" +
            "Z0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    public BitmapFont main;
    public BitmapFont main_big;

    public Fonts() {

        FreeTypeFontGenerator minecraft = new FreeTypeFontGenerator(Gdx.files.internal("hacked.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;
        parameter.size = 40;
        parameter.color = Color.WHITE;
        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;
        main = minecraft.generateFont(parameter);

        parameter.size = 72;
        main_big = minecraft.generateFont(parameter);

        minecraft.dispose();

    }

    public void dispose() {
        main.dispose();
    }

}