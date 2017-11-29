package inc.tortuga.sugarboy.quentinmars.utils.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.logic.Matrix.MathMatrix;
import inc.tortuga.sugarboy.quentinmars.utils.logic.Matrix.Pair;
import inc.tortuga.sugarboy.quentinmars.utils.logic.entity.Player;

/**
 * Created by swift on 12.11.2017.
 */

public class World {

    private static final float k = Game.get().getConfig().k;

    private TiledMap map;

    private int width = 0;
    private int height = 0;
    private MathMatrix<TiledMapTileLayer.Cell> blocks;
    private MathMatrix<TiledMapTileLayer.Cell> coins;
    private MathMatrix<Boolean> loaded;
    private Pair<Integer> start;
    private Pair<Integer> end;
    private Player player;

    public Player setPlayer() {
        return this.player = new Player(this);
    }

    public World(int level) {

        this.map = new TmxMapLoader().load(String.format("tiled\\level_%s\\map.tmx", level));
        for (MapLayer layer : map.getLayers()) {

            if (width == 0) {
                this.width = map.getProperties().get("width", Integer.class);
                this.height = map.getProperties().get("height", Integer.class);
                this.blocks = new MathMatrix<TiledMapTileLayer.Cell>(width, height);
                this.coins = new MathMatrix<TiledMapTileLayer.Cell>(width, height);
                this.loaded = new MathMatrix<Boolean>(width, height);
            }

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) layer).getCell(x, y);
                    if (cell == null) continue;

                    switch (cell.getTile().getId() - 1) {
                        case 2:
                            blocks.set(x, y, cell);
                            break;
                        case 1:
                            coins.set(x, y, cell);
                            break;
                        case 4:
                            start = new Pair<Integer>(x, y);
                            break;
                        case 3:
                            end = new Pair<Integer>(x, y);
                            break;
                    }
                }
            }
        }
    }

    /**
     * Отрисовываем вид игрока
     **/
    public void renderPlayerView(SpriteBatch sb, OrthogonalTiledMapRenderer renderer) {
        MathMatrix<Integer> matrix = getCellsForRender();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int q = matrix.get(x, y);

                if (q != 0) {

                    if (blocks.get(x, y) != null) {
                        Color color = sb.getColor();
                        sb.setColor(0.15F, 0.15F, 0.15F, 0.45F);
                        new TextureRegionDrawable(((TiledMapTileLayer)renderer.getMap().getLayers().get("w")).getCell(x, y).getTile().getTextureRegion()).draw(sb, x * 32 * k, y * 32 * k, 32 * k, 32 * k);
                        sb.setColor(color);
                        continue;
                    }

                    /** Полный рендер **/
                    for (MapLayer layer : renderer.getMap().getLayers()) {
                        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) layer).getCell(x, y);
                        if (cell == null) continue;
                        TextureRegionDrawable region = new TextureRegionDrawable(cell.getTile().getTextureRegion());

                        if (q == 1) {
                            if (layer.getName().equalsIgnoreCase("i")) continue;
                            Color color = sb.getColor();
                            sb.setColor(0.15F, 0.15F, 0.15F, 0.4F);
                            region.draw(sb, x * 32 * k, y * 32 * k, 32 * k, 32 * k);
                            sb.setColor(color);
                        } else region.draw(sb, x * 32 * k, y * 32 * k, 32 * k, 32 * k);

                    }

                }

            }
        }
    }

    public void update(float dt) {
        player.update(dt);
    }

    private MathMatrix<Integer> getCellsForRender() {
        /**
         * 2 - full render
         * 1 - part render
         * 0 - not render
         * **/
        MathMatrix<Integer> matrix = new MathMatrix<Integer>(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                matrix.set(x, y, loaded.get(x, y) != null ? 1 : 0);
            }
        }

        matrix.set(player.getX(), player.getY(), 2);

        for (double degree = -player.getDegreeVisible(); degree <= player.getDegreeVisible(); degree += 1) {
            for (double r = 1; r <= player.getRadiusVisible(); r++) {

                int x = (int) Math.round(player.getX() + r * Math.cos(Math.toRadians(player.getDegreeVisibleAngle() + degree)));
                int y = (int) Math.round(player.getY() + r * Math.sin(Math.toRadians(player.getDegreeVisibleAngle() + degree)));

                loaded.set(x, y, true);
                matrix.set(x, y, 2);

                if (blocks.get(x, y) != null) break;

            }
        }

        return matrix;
    }

    public TiledMap getMap() {
        return map;
    }

    public MathMatrix<TiledMapTileLayer.Cell> getBlocks() {
        return blocks;
    }

    public MathMatrix<TiledMapTileLayer.Cell> getCoins() {
        return coins;
    }

    public Pair<Integer> getStart() {
        return start;
    }

    public Pair<Integer> getEnd() {
        return end;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }


}
