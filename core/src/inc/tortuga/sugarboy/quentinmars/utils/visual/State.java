package inc.tortuga.sugarboy.quentinmars.utils.visual;

/**
 * Created by swift on 22.10.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import inc.tortuga.sugarboy.quentinmars.Game;

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 touch;
    protected StateManager manager;

    public State(StateManager manager) {
        this.manager = manager;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        touch = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}