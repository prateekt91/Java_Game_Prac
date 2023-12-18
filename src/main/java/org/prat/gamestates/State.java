package org.prat.gamestates;

import org.prat.Game;

public class State {

    protected Game game;
    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
