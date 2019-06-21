package com.grupo73.proj1;


import java.io.IOException;

public class State {
    private static StateObserver stateObserver;

    public enum STATE {GAME, MENU}
    private State.STATE state;

    public State(StateObserver obsever) throws IOException {
        this.state = STATE.MENU;
        this.stateObserver = obsever;
    }

    public State.STATE getState() {
        return state;
    }

    public void setState(State.STATE state) {
        this.state = state;
    }

    public static void notifyObserver() {
        try {
            stateObserver.update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
