package inc.tortuga.sugarboy.quentinmars.utils.visual.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.awt.Rectangle;

import inc.tortuga.sugarboy.quentinmars.Game;

/**
 * Created by swift on 30.10.2017.
 */

public class GameDiv extends Actor {

    private Table table;
    private static final float k = Game.get().getConfig().k;


    public GameDiv(float x, float y) {
        table = new Table();
    }

    public Cell<Actor> addActor(Actor actor) {
        table.add(actor);
        table.row();
        return table.getCells().peek();
    }

    public Cell<Actor> addActor(Actor actor, float width) {
        return this.addActor(actor).width(width).pad(15F * k);
    }

    public Cell<Actor> addActor(Actor actor, float width, float pad) {
        return this.addActor(actor).width(width).pad(pad);
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
