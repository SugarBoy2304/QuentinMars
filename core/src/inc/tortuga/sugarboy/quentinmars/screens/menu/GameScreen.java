package inc.tortuga.sugarboy.quentinmars.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Timer;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.visual.ButtonStyle;
import inc.tortuga.sugarboy.quentinmars.utils.visual.FontUtils;
import inc.tortuga.sugarboy.quentinmars.utils.visual.State;
import inc.tortuga.sugarboy.quentinmars.utils.visual.StateManager;

/**
 * Created by swift on 28.10.2017.
 */

public class GameScreen extends State {

    private TextButton place[][];
    private ButtonStyle styles;
    private Stage stage;
    private boolean isX;
    private int count;

    private boolean warnBusy;
    private final int lenght = 3;

    public GameScreen(StateManager manager) {
        super(manager);

        stage = new Stage();
        styles = new ButtonStyle();
        restart();

        Gdx.input.setInputProcessor(stage);
    }

    public void restart() {
        count = 0;
        place = new TextButton[lenght][lenght];
        isX = true;

        for (int x = 0; x < lenght; x++) {
            for (int y = 0; y < lenght; y++) {
                final TextButton button = new TextButton(" ", styles.style());
                button.setSize(100F, 100F);
                button.setPosition(
                        /* TODO Ширина экрана / 2 + (-105 или 0 или +105) - длина кнопки /2 */
                        Gdx.graphics.getWidth() / 2 + ((-(lenght - 1) / 2) + x) * 105F - (button.getWidth() / 2),
                        Gdx.graphics.getHeight() / 2 + ((-(lenght - 1) / 2) + y) * 105F - (button.getHeight() / 2)
                );
                final int _x = x;
                final int _y = y;
                button.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float xx, float yy, int pointer, int b) {
                        if (!button.getText().toString().equals(" ")) {
                            warnBusy = true;
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    warnBusy = false;
                                }
                            }, 2F);
                            return true;
                        }

                        button.setText(isX ? "1" : "O");
                        button.setStyle(isX ? styles.forX() : styles.forO());

                        if (checkWin(_x, _y)) {
                            setWin();
                            return true;
                        }

                        count++;
                        if (count == lenght * lenght) Timer.schedule(new Timer.Task() { public void run() {restart();} }, 0.5F);
                        isX = !isX;
                        return true;
                    }
                });
                place[x][y] = button;
                stage.addActor(button);

            }
        }


    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        if (warnBusy)
            FontUtils.sendCenter(Game.get().getFonts().main, sb, "Данная ячейка уже занята", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 80);
        for (TextButton[] buttons : place) for (TextButton button : buttons) button.draw(sb, 1);
        sb.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    protected void handleInput() {

    }

    public void setWin() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                manager.push(new WinGameScreen(manager, isX ? '1' : 'O'));
            }
        }, 0.5F);
    }

    public boolean checkWin(int _x, int _y) {
        String lines = getLine(_x, _y, 1, 0) + getLine(_x, _y, 0, 1) + getLine(_x, _y, 1, 1) + getLine(_x, _y, 1, -1);
        Gdx.app.log("TestWin", lines.replace(" ", "_"));
        return lines.contains("111") || lines.contains("OOO");
    }

    private String getLine(int _x, int _y, int offsetX, int offsetY) {
        String line = "";
        for (int q = -2; q < 3; q++) {
            int x = _x + q * offsetX;
            int y = _y + q * offsetY;
            if (x < 0 || y < 0 || x > lenght - 1 || y > lenght - 1) continue;
            line += place[x][y].getText();
        }
        Gdx.app.log("TestWin", line.replace(" ", "_"));
        return line + "-";
    }


}
