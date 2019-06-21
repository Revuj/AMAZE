package com.grupo73.proj1.Controller;

import com.grupo73.proj1.Controller.Entities.HeroController;
import com.grupo73.proj1.Model.GameModel;
import com.grupo73.proj1.View.Event;

public class GameController implements Controller<GameModel> {

    public static final int ARENA_WIDTH = 54;
    public static final int ARENA_HEIGHT = 19;

    private HeroController hero;

    public  GameController() {
        this.hero = new HeroController();
    }

    @Override
    public void consumeEvent(Event event, GameModel game) {
        hero.consumeEvent(event, game.getHero());
    }

    public HeroController getHero() {
        return hero;
    }

}
