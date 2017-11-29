package inc.tortuga.sugarboy.quentinmars.utils.logic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by swift on 12.11.2017.
 */

public abstract class GameState extends State {

    public GameState(final StateManager manager) {
        super(manager);
    }

    public abstract void update(float v);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();

    public abstract void read();


}
