package inc.tortuga.sugarboy.quentinmars.utils.visual;

/**
 * Created by swift on 28.10.2017.
 */

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.TimeUtils;

public class FontUtils {

    private static GlyphLayout glyphLayout = new GlyphLayout();
    private static TextMessage textBonus;

    public static int sendLeft(BitmapFont font, Batch batch, String text, float x, float y) {
        glyphLayout.setText(font, text);
        font.draw(batch, glyphLayout, x, y);
        return (int) glyphLayout.width;
    }

    public static int sendCenter(BitmapFont font, Batch batch, String text, float x, float y) {
        if (text.contains("/n")) {
            String[] texts = text.split("/n");
            int plusY = 0;
            for (String s : texts) {
                glyphLayout.setText(font, s);
                font.draw(batch, glyphLayout, x - glyphLayout.width / 2, y + plusY);
                plusY -= glyphLayout.height * 1.15;
            }
        } else {
            glyphLayout.setText(font, text);
            font.draw(batch, glyphLayout, x - glyphLayout.width / 2, y);
        }
        return (int) glyphLayout.width;
    }

    public static int sendRight(BitmapFont font, Batch batch, String text, float x, float y) {
        glyphLayout.setText(font, text);
        font.draw(batch, glyphLayout, x - glyphLayout.width, y);
        return (int) glyphLayout.width;
    }

    public static void sendCenter(BitmapFont font, String text, float x, float y, long time) {
        textBonus = new TextMessage(font, text, x, y, time);
    }

    public static void draw(Batch batch) {
        if (textBonus != null) {
            sendCenter(textBonus.font, batch, textBonus.text, textBonus.x, textBonus.y);
            if (textBonus.time < TimeUtils.nanoTime()) textBonus = null;
        }
    }

    public static GlyphLayout getLayout() {
        return glyphLayout;
    }

    public static class TextMessage {

        public BitmapFont font;
        public String text;
        public float x;
        public float y;
        public long time;

        public TextMessage(BitmapFont font, String text, float x, float y, long time) {
            this.font = font; this.text = text; this.x = x; this.y = y; this.time = TimeUtils.nanoTime() + 50000000L * time;
        }

    }

}
