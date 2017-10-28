package inc.tortuga.sugarboy.quentinmars.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.visual.ButtonStyle;
import inc.tortuga.sugarboy.quentinmars.utils.visual.FontUtils;
import inc.tortuga.sugarboy.quentinmars.utils.visual.State;
import inc.tortuga.sugarboy.quentinmars.utils.visual.StateManager;

/**
 * Created by swift on 28.10.2017.
 */

public class WinGameScreen extends State {

    private Stage stage;
    private TextButton button;
    private ButtonStyle styles;

    private char winner;

    public WinGameScreen(final StateManager manager, char winner) {
        super(manager);
        this.winner = winner;

        stage = new Stage();
        styles = new ButtonStyle();

        button = new TextButton("В главное меню", winner == '1' ? styles.forX() : styles.forO());
        button.pad(20F);
        button.setSize(400F, 120F);
        button.setPosition(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2, 80);

        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int b) {
                manager.push(new MainMenuScreen(manager));
                dispose();
            }
        });

        Gdx.input.setInputProcessor(stage);

        stage.addActor(button);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        FontUtils.sendCenter(Game.get().getFonts().main, sb, "Победили " + (winner == '1' ? "Единички" : "Нолики"), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 80);
        button.draw(sb, 1);
        sb.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    protected void handleInput() {

    }

}
