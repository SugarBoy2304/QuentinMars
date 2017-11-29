package inc.tortuga.sugarboy.quentinmars.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.visual.FontUtils;
import inc.tortuga.sugarboy.quentinmars.utils.logic.State;
import inc.tortuga.sugarboy.quentinmars.utils.logic.StateManager;
import inc.tortuga.sugarboy.quentinmars.utils.visual.ui.GameButton;

/**
 * Created by swift on 28.10.2017.
 */

public class AuthorsMenuScreen extends State {

    private Stage stage;
    private GameButton button;
    private Texture background;
    private Skin skin;
    private float x;

    private float[] textRec;

    public AuthorsMenuScreen(final StateManager manager, float X) {
        super(manager);
        this.x = X;

        stage = new Stage();
        skin = new Skin(new TextureAtlas("ui/gui.atlas"));

        background = new Texture("ui/menu/background.jpg");

        button = new GameButton(lang().format("back"));
        button.a().pad(20F * _x);
        button.a().setWidth(250F * _x);
        button.a().setHeight(70F * _y);
        button.setPosition(465F * _x, -300F * _y);
        button.updateListener();
        button.eventOff(new Runnable() {
            @Override
            public void run() {
                manager.push(new MainMenuScreen(manager, x));
            }
        });

        stage.addActor(button.a());

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float dt) {
        x += dt;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0F, 0F, 1280F * _x, 720F * _y);

        FontUtils.sendLeft(Game.get().getFonts().main_big, sb, "Quentin Mars",
                -540F * _x + w((float) Math.cos(x) * 15F),
                240F * _y + h((float) Math.sin(x) * 50F));

        if (textRec != null) skin.getDrawable("div").draw(sb,
                w(0F, textRec[0] * 1.15F / _x),
                h(0F, textRec[1] * 1.15F / _y),
                textRec[0] * 1.15F,
                textRec[1] * 1.15F);

        textRec = FontUtils.sendMiddleCenter(Game.get().getFonts().orbitron, sb, w(0F), h(0F), lang().format("authors-more"));
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        if (background != null) background.dispose();
        skin.getAtlas().dispose();
        skin.dispose();
    }

}
