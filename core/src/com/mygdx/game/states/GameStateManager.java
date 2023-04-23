package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.API;

import java.util.Stack;

public class GameStateManager {

    private final Stack<State> states;

    private final API api;

    public GameStateManager(API api) {
        states = new Stack<>();
        this.api = api;
    }

    public API getApi() {
        return this.api;
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop();
    }

    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);

    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }


}
