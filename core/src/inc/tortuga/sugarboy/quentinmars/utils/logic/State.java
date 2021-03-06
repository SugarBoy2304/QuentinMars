package inc.tortuga.sugarboy.quentinmars.utils.logic;

/**
 * Created by swift on 22.10.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.I18NBundle;

import inc.tortuga.sugarboy.quentinmars.Game;

public abstract class State {

    protected OrthographicCamera camera;
    protected StateManager manager;

    protected float _x;
    protected float _y;

    public State(final StateManager manager) {
        this.manager = manager;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        _x = Gdx.graphics.getWidth() / 1280F;
        _y = Gdx.graphics.getHeight() / 720F;
    }

    public I18NBundle lang() {
        return Game.get().getConfig().getLang();
    }

    public float w(float w, float W) {
        return (Gdx.graphics.getWidth() / 2F  + w * _x - (W / 2F * _x));
    }

    public float w(float w) {
        return this.w(w, 0F);
    }

    public float h(float h, float H) {
        return (Gdx.graphics.getHeight() / 2F + h * _y - (H / 2F * _y) );
    }

    public float h(float h) {
        return this.h(h, 0F);
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }


    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}