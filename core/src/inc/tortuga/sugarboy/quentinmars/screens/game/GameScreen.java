package inc.tortuga.sugarboy.quentinmars.screens.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import inc.tortuga.sugarboy.quentinmars.utils.logic.GameState;
import inc.tortuga.sugarboy.quentinmars.utils.logic.StateManager;
import inc.tortuga.sugarboy.quentinmars.utils.logic.World;
import inc.tortuga.sugarboy.quentinmars.utils.logic.entity.Player;

/**
 * Created by swift on 12.11.2017.
 */

public class GameScreen extends GameState {

    private static int LEVEL;

    private static World world;
    private OrthogonalTiledMapRenderer renderer;

    private Player player;

    public GameScreen(final StateManager manager, int level) {
        super(manager);
        this.LEVEL = level;
        this.world = new World(level);
        this.player = world.setPlayer();

        renderer = new OrthogonalTiledMapRenderer(world.getMap(), _x);
        renderer.setView(this.camera);
    }

    public void update(float dt) {
        world.update(dt);
    }


    public void render(SpriteBatch sb) {

        sb.begin();
        world.renderPlayerView(sb, renderer);
        player.draw(sb);
        sb.end();
    }

    public void read() {


    }

    public void dispose() {

    }
}
