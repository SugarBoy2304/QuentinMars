package inc.tortuga.sugarboy.quentinmars.utils.visual;

/**
 * Created by swift on 28.10.2017.
 */

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class FontUtils {

    private static GlyphLayout glyphLayout = new GlyphLayout();
    private static TextMessage textBonus;

    public static int sendLeft(BitmapFont font, Batch batch, String text, float x, float y) {
        glyphLayout.setText(font, text);
        font.draw(batch, glyphLayout, x, y);
        return (int) glyphLayout.width;
    }

    public static float[] sendCenter(BitmapFont font, Batch batch, String text, float x, float y) {
        if (text.contains("\n")) {
            String[] texts = text.split("\n");
            int plusY = 0;
            int maxWidth = 0;
            for (String s : texts) {
                glyphLayout.setText(font, s);
                font.draw(batch, glyphLayout, x - glyphLayout.width / 2F, y + plusY);
                if (maxWidth < glyphLayout.width) maxWidth = (int) glyphLayout.width;
                plusY -= glyphLayout.height * (s.equals("") ? 0.75F : 1.25F);
            }
            return new float[]{maxWidth, -plusY};
        } else {
            glyphLayout.setText(font, text);
            font.draw(batch, glyphLayout, x - glyphLayout.width / 2F, y);
        }
        return new float[]{glyphLayout.width, glyphLayout.height};
    }

    public static float[] sendMiddleCenter(BitmapFont font, SpriteBatch batch, float x, float y, String[] text) {

        glyphLayout.setText(font, text[0]);
        int height = 0;
        for (String s : text) {
            height += (s.length() == 0 ? 0.75F : 1.45F) * glyphLayout.height;
        }

        int plusY = 0;
        int maxWidth = 0;
        for (String s : text) {
            glyphLayout.setText(font, s);
            font.draw(batch, glyphLayout, x - glyphLayout.width / 2F, y + plusY + height / 2F);
            if (maxWidth < glyphLayout.width) maxWidth = (int) glyphLayout.width;
            plusY -= glyphLayout.height * (s.equals("") ? 0.75F : 1.45F);
        }
        return new float[]{maxWidth, height};
    }

    public static float[] sendMiddleCenter(BitmapFont font, SpriteBatch batch, float x, float y, String text) {
        String[] lines = text.split("\n");
        return sendMiddleCenter(font, batch, x, y, lines);
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
