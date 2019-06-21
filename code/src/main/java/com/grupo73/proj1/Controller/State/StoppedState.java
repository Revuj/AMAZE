package com.grupo73.proj1.Controller.State;

import com.grupo73.proj1.Model.Entities.HeroModel;
import com.grupo73.proj1.View.Event;

public class StoppedState implements EntityState {
    @Override
    public void consumeEvent(Event event, HeroModel hero) {
        Event.EVENT type = event.getType();

        switch (type) {
            case UP:
                up.execute(hero);
                break;
            case DOWN:
                down.execute(hero);
                break;
            case LEFT:
                left.execute(hero);
                break;
            case RIGHT:
                right.execute(hero);
                break;
        }
    }

    @Override
    public void update(HeroModel hero) {

    }
}
