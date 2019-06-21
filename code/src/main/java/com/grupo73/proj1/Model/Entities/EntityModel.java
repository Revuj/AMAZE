package com.grupo73.proj1.Model.Entities;

import com.grupo73.proj1.Controller.CommandInput.Position;
import com.grupo73.proj1.Controller.State.EntityState;
import com.grupo73.proj1.Controller.State.StoppedState;

public abstract class EntityModel {

    private Position position;
    private EntityState state;


    public EntityModel(int x, int y) {
        this.position = new Position(x,y);
        state = new StoppedState();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
            this.state = state;
    }
}
