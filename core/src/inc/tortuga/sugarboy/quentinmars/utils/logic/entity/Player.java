package inc.tortuga.sugarboy.quentinmars.utils.logic.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

import inc.tortuga.sugarboy.quentinmars.Game;
import inc.tortuga.sugarboy.quentinmars.utils.logic.World;
import inc.tortuga.sugarboy.quentinmars.utils.logic.entity.interfaces.Entity;
import inc.tortuga.sugarboy.quentinmars.utils.logic.entity.interfaces.EntityLiving;
import inc.tortuga.sugarboy.quentinmars.utils.logic.items.Item;

/**
 * Created by swift on 27.11.2017.
 */

public class Player implements EntityLiving {

    private static final float k = Game.get().getConfig().k;
    private static final long updateTick = 1000;
    private static final float rotationSpeed = 250F;
    private static Vector2 baseVector = new Vector2(1, 0);


    private long lastUpdate = 0;

    private World world;
    private int x;
    private int y;
    private SpriteDrawable sprite;
    private boolean isAlive = true;
    private float health = 100;
    private float angle;
    private Vector2 viewVector;
    private Vector2 seekVector;

    private ShapeRenderer r;

    public Player(World world) {
        this.world = world;
        sprite = new SpriteDrawable(new Sprite(new TextureRegion(new Texture("tiled\\player.png"), 32, 32)));
        this.x = world.getStart().a;
        this.y = world.getStart().b;
        this.viewVector = new Vector2(1, 0);
        this.seekVector = new Vector2(0, 1);

        r = new ShapeRenderer();
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void draw(SpriteBatch sb) {
        sprite.draw(sb, (x * 32F) * k, (y * 32F) * k, 0F, 0F, sprite.getSprite().getWidth(), sprite.getSprite().getHeight(), k, k, 0);

        //r.setProjectionMatrix(Game.get().getStateManager().getState().getCamera().combined);
        //r.begin(ShapeRenderer.ShapeType.Line);
        //r.line(x * 32, y * 32, x * 32 + (viewVector.x * getRadiusVisible() * 32), y * 32 + (viewVector.y * getRadiusVisible() * 32), Color.GOLD, Color.GOLD);
        //r.line(x * 32, y * 32, x * 32 + (seekVector.x * getRadiusVisible() * 32), y * 32 + (seekVector.y * getRadiusVisible() * 32), Color.RED, Color.RED);
        //r.end();
    }

    @Override
    public boolean addPosition(int x, int y) {
        seekVector.set(x, y);
        System.out.print(seekVector);
        if (this.x + x >= world.getWidth() || this.x + x < 0 || world.getBlocks().get(this.x + x, this.y) != null ||
                this.y + y >= world.getHeight() || this.y + y < 0 || world.getBlocks().get(this.x, this.y + y) != null) return false;
        setPosition(this.x + x, this.y + y);
        return true;
    }

    @Override
    public void dispose() {
        sprite.getSprite().getTexture().dispose();
    }

    @Override
    public void update(double dt) {

        if (viewVector.x != seekVector.x || viewVector.y != seekVector.y) {
            int arg = viewVector.angle(seekVector) > 0 ? 1 : -1;
            viewVector.rotate((float) (rotationSpeed * arg * dt));
        }

        if (Math.abs(seekVector.angle(baseVector) - viewVector.angle(baseVector)) < 5) {
            viewVector.set(seekVector);
        }

        if (!isCanUpdate()) return;

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            up();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            down();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            right();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            left();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            action();
        }

    }

    @Override
    public boolean isCanUpdate() {
        if (lastUpdate + updateTick <= System.nanoTime()) {
            lastUpdate = System.nanoTime();
            return true;
        }
        return false;
    }

    @Override
    public boolean isDie() {
        return isAlive;
    }

    @Override
    public boolean up() {
        if (addPosition(0, 1)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean down() {
        if (addPosition(0, -1)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean left() {
        if (addPosition(-1, 0)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean right() {
        if (addPosition(1, 0)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public boolean damageTo(Entity entity, float damage) {
        return false;
    }

    @Override
    public float getFinalDamage(Entity entity, float damage) {
        return 0;
    }

    @Override
    public void setDrop(Item... items) {

    }

    @Override
    public void setDrop(Array<Item> items) {

    }

    @Override
    public boolean isRespawnable() {
        return false;
    }

    @Override
    public long getTimeToRespawn() {
        return 0;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public float getHealth() {
        return 0;
    }

    @Override
    public float removeHealth(float health) {
        return 0;
    }

    @Override
    public int getRadiusVisible() {
        return 7;
    }

    @Override
    public int getDegreeVisible() {
        return 45;
    }

    @Override
    public int getDegreeVisibleAngle() {
        return (int) -viewVector.angle(baseVector);
    }

    @Override
    public void die() {
        this.isAlive = false;
    }
}
