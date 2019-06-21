package com.grupo73.proj1.Controller.CommandInput;

import com.grupo73.proj1.Model.Entities.EntityModel;

public interface Command {
    void execute(EntityModel model);
}
