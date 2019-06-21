package com.grupo73.proj1.Controller.CommandInput;

import com.grupo73.proj1.Controller.State.MovingUpState;
import com.grupo73.proj1.Model.Entities.EntityModel;

public class UpCommand implements Command {

    @Override
    public void execute(EntityModel model) {
        model.setState(new MovingUpState());
    }
}
