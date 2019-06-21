package com.grupo73.proj1.Controller.State;

import com.grupo73.proj1.Controller.CommandInput.DownCommand;
import com.grupo73.proj1.Controller.CommandInput.LeftCommand;
import com.grupo73.proj1.Controller.CommandInput.RightCommand;
import com.grupo73.proj1.Controller.CommandInput.UpCommand;
import com.grupo73.proj1.Controller.Controller;
import com.grupo73.proj1.Model.Entities.HeroModel;
import com.grupo73.proj1.View.Event;

public interface EntityState extends Controller<HeroModel> {

     UpCommand up = new UpCommand();
     DownCommand down = new DownCommand();
     LeftCommand left = new LeftCommand();
     RightCommand right = new RightCommand();

     void consumeEvent(Event event, HeroModel hero);

     void update(HeroModel hero);
}
