package com.grupo73.proj1.Controller;

import com.grupo73.proj1.Model.Menu.MenuModel;
import com.grupo73.proj1.View.Event;

public class MenuController implements  Controller<MenuModel> {

    @Override
    public void consumeEvent(Event event, MenuModel model)  {
        if (event.getType().equals(Event.EVENT.UP)) {
            model.updateModelUp();
        }
        else if (event.getType().equals(Event.EVENT.DOWN)) {
            model.updateModelDown();
        }
    }
}
