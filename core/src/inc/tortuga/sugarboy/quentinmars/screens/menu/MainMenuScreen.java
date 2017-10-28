package inc.tortuga.sugarboy.quentinmars.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.visual.ButtonStyle;
import inc.tortuga.sugarboy.quentinmars.utils.visual.FontUtils;
import inc.tortuga.sugarboy.quentinmars.utils.visual.State;
import inc.tortuga.sugarboy.quentinmars.utils.visual.StateManager;

/**
 * Created by swift on 22.10.2017.
 */

public class MainMenuScreen extends State {

    private int x;

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton button;
    private ButtonStyle styles;

    public MainMenuScreen(final StateManager manager) {
        super(manager);

        stage = new Stage();
        styles = new ButtonStyle();

        atlas = new TextureAtlas("ui/gui.atlas");
        skin = new Skin(atlas);
        table = new Table(skin);

        button = new TextButton("Играть", styles.style());
        button.pad(20F);
        button.setSize(400F, 120F);

        /* TODO Длина всего экрана / 2 - (длина кнопки / 2) */
        button.setPosition(Gdx.graphics.getWidth() / 2 - (button.getWidth() / 2), 80);

        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manager.push(new GameScreen(manager));
                dispose();
                return true;
            }
        });

        table.add(button);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        stage.act(dt);
        x += dt * 100;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        FontUtils.sendCenter(Game.get().getFonts().main_big, sb, "Единички-нолики", Gdx.graphics.getWidth() / 2, (int) ((float) (Math.sin(x * 0.025) * 100) + 460));
        button.draw(sb, 1);

        sb.end();
    }

    @Override
    public void dispose() {
        atlas.dispose();
        skin.dispose();

    }

}
