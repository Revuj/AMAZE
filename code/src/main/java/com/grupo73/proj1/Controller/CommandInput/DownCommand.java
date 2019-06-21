package com.grupo73.proj1.Controller.CommandInput;

import com.grupo73.proj1.Controller.State.MovingDownState;
import com.grupo73.proj1.Model.Entities.EntityModel;

public class DownCommand implements Command {

    @Override
    public void execute(EntityModel model) {
        model.setState(new MovingDownState());
    }
}
