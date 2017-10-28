package inc.tortuga.sugarboy.quentinmars.utils.visual;

/**
 * Created by swift on 22.10.2017.
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class StateManager {

    private Stack<State> states;

    public StateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void setState(State state) {
        pop(); push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }

    public State getState() {
        return this.states.peek();
    }

}
