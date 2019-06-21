package com.grupo73.proj1.Controller.Entities;

import com.grupo73.proj1.Controller.CommandInput.Position;
import com.grupo73.proj1.Controller.Controller;
import com.grupo73.proj1.Controller.State.StoppedState;
import com.grupo73.proj1.Model.Entities.HeroModel;
import com.grupo73.proj1.Model.GameModel;
import com.grupo73.proj1.View.Event;

import java.io.IOException;

public class HeroController implements Controller<HeroModel> {

    @Override
    public void consumeEvent(Event event, HeroModel hero) {
        hero.getState().consumeEvent(event, hero);
    }

    boolean portalsExist(GameModel model) {
        return(model.getPortalsPosition().size() != 0);
    }

    void teleport(GameModel model) {
        HeroModel hero = model.getHero();

        if (model.getPortalsPosition().get(0).equals(hero.getPosition())) {
            model.getHero().disablePortals();
            hero.setPosition(model.getPortalsPosition().get(1).copyPosition());
        } else if (model.getPortalsPosition().get(1).equals(hero.getPosition())) {
            model.getHero().disablePortals();
            hero.setPosition(model.getPortalsPosition().get(0).copyPosition());
        }
    }


    public void move(GameModel model) throws IOException {
        HeroModel hero = model.getHero();

        Position oldPosition =  new Position(hero.getPosition().getX(), hero.getPosition().getY());

        model.getHero().getState().update(model.getHero());

        if (model.getWallsPositions().contains(hero.getPosition())) {
            hero.setPosition(oldPosition);
            hero.setState(new StoppedState());
        }

        if (model.getHero().getPosition().equals(model.getExit().getPosition())) {
            model.updateGameModel();
        }

        if (model.getCoinPostions().contains(hero.getPosition())) {
            model.updateCoins(hero.getPosition());
        }

        if (portalsExist(model)) {
            if (model.getHero().getPortalsEnabled()) {
                teleport(model);
            } else if (!model.getPortalsPosition().contains(hero.getPosition())){
                model.getHero().justUsedPortal();
            }
        }
    }
}
