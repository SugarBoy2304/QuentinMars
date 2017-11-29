package inc.tortuga.sugarboy.quentinmars.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.screens.game.*;
import inc.tortuga.sugarboy.quentinmars.screens.game.GameScreen;
import inc.tortuga.sugarboy.quentinmars.utils.visual.FontUtils;
import inc.tortuga.sugarboy.quentinmars.utils.logic.State;
import inc.tortuga.sugarboy.quentinmars.utils.logic.StateManager;
import inc.tortuga.sugarboy.quentinmars.utils.visual.ui.GameButton;

/**
 * Created by swift on 22.10.2017.
 */

public class MainMenuScreen extends State {

    private float x;

    private Stage stage;
    private Table table;
    private Texture background;

    public MainMenuScreen(final StateManager manager, float X) {
        super(manager);
        this.x = X;

        stage = new Stage();
        table = new Table();

        background = new Texture("ui/menu/background.jpg");

        stage.addActor(new Actor());

        GameButton buttonPlay = new GameButton(lang().format("play"));
        buttonPlay.a().pad(20F * _y);
        buttonPlay.a().setHeight(40F * _y);
        table.add(buttonPlay.a()).pad(10F * _y).width(250F * _x);
        table.row();
        buttonPlay.eventOff(new Runnable() {
            @Override
            public void run() {
                manager.push(new GameScreen(manager, 1));
            }
        });

        GameButton buttonSetting = new GameButton(lang().format("settings"));
        buttonSetting.a().pad(20F * _y);
        buttonSetting.a().setHeight(40F * _y);
        table.add(buttonSetting.a()).pad(10F * _y).width(250F * _x);
        table.row();

        GameButton buttonAuthors = new GameButton(lang().format("authors"));
        buttonAuthors.a().pad(20F * _y);
        buttonAuthors.a().setHeight(40F * _y);
        table.add(buttonAuthors.a()).pad(10F * _y).width(250F * _x);
        table.row();
        buttonAuthors.eventOff(new Runnable() {
            @Override
            public void run() {
                manager.push(new AuthorsMenuScreen(manager, x));
            }
        });

        table.setPosition(Gdx.graphics.getWidth() - 175F * _x, 150F * _y);

        stage.addActor(table);
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
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }

}
