package com.grupo73.proj1.Controller.State;

import com.grupo73.proj1.Model.Entities.HeroModel;
import com.grupo73.proj1.View.Event;

public class MovingUpState implements EntityState {
    @Override
    public void consumeEvent(Event event, HeroModel hero) {

    }

    @Override
    public void update(HeroModel hero) {
        hero.getPosition().setY(hero.getPosition().getY()-1);
    }
}
