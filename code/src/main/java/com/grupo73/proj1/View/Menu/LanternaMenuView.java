package com.grupo73.proj1.View.Menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.grupo73.proj1.Controller.GameController;
import com.grupo73.proj1.MenuState;
import com.grupo73.proj1.Model.Menu.MenuModel;
import com.grupo73.proj1.StateObserver;
import com.grupo73.proj1.View.Event;
import com.grupo73.proj1.View.View;

import java.io.IOException;

public class LanternaMenuView implements View<MenuModel> {

    private StringBuilder builder;
    private Event event;
    public static Screen screen;
    public static TextGraphics graphics;

    public LanternaMenuView() throws IOException {
        this.screen = StateObserver.screen;
        builder = new StringBuilder();
        this.event = new Event();

    }


    @Override
    public void draw(MenuModel model) throws IOException {
        screen.clear();
        graphics = screen.newTextGraphics();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(GameController.ARENA_WIDTH-1, GameController.ARENA_HEIGHT), ' ');

        builder.setLength(0);


        for(int i = 0 ; i < model.getOptions().size() ; i++) {
            if (i == model.getSelectedOption()) {
                builder.append(" * ");
            } else {
                builder.append("   ");
            }
            builder.append(i);
            builder.append("-");
            builder.append(model.getOptions().get(i));
            this.graphics.putString(new TerminalPosition(model.getPosition().getX(), model.getPosition().getY() + i), builder.toString());
            builder.setLength(0);


        }


        screen.refresh();

    }

    private void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();

            switch(character) {
                case 'w':
                    event.setType(Event.EVENT.UP);
                    break;
                case 's':
                    event.setType(Event.EVENT.DOWN);
                    break;
                case ' ':
                    event.setType(Event.EVENT.ENTER);
                    break;
                case 'q':
                    event.setType(Event.EVENT.CLOSE);
                default:
                    event.setType(Event.EVENT.IGNORE);
                    break;
            }

        }

        MenuState.notifyEvent(event);

    }

    public void readInput() throws IOException {
        KeyStroke key = screen.readInput();
        processKey(key);
    }
}
