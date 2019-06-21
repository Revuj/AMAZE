package com.grupo73.proj1.View.Game;

import com.grupo73.proj1.Controller.CommandInput.Position;
import com.grupo73.proj1.Controller.GameController;
import com.grupo73.proj1.GameState;
import com.grupo73.proj1.Model.GameModel;
import com.grupo73.proj1.StateObserver;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.grupo73.proj1.View.Event;
import com.grupo73.proj1.View.View;

import java.io.IOException;
import java.util.Set;

public class LanternaGameView implements View<GameModel> {

    public static Screen screen;
    public static TextGraphics graphics;
    private StringBuilder stringBuilder = new StringBuilder("Score:");
    private final int scoreLength=6;
    private Event event;

    public LanternaGameView() {
        this.screen = StateObserver.screen;
        event = new Event();
    }

    @Override
    public void draw(GameModel model) throws IOException {
        screen.clear();
        graphics = screen.newTextGraphics();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(GameController.ARENA_WIDTH-1, GameController.ARENA_HEIGHT), ' ');

        drawWalls(model);
        drawExit(model);
        drawScore(model);
        drawCoins(model);
        drawPortals(model);
        drawHero(model);

        screen.refresh();
    }

    private void draw(String symbol, Position position, TextColor backgroundColor , TextColor foregroundColor) {
        LanternaGameView.graphics.setBackgroundColor(backgroundColor);
        LanternaGameView.graphics.setForegroundColor(foregroundColor);
        LanternaGameView.graphics.putString(new TerminalPosition(position.getX(), position.getY()), symbol);
    }

    private void drawPortals(GameModel model) {
        for(Position position : model.getPortalsPosition()) {
            model.getPortal().setPosition(position);
            TextColor.RGB backgroundColor = BackGroundColorFactory.getColor("≎");
            TextColor.RGB foregroundColor = ForeGroundColorFactory.getColor("≎");
            draw("≎", position, backgroundColor,foregroundColor);
        }
    }


    private void drawScore(GameModel model) {
        stringBuilder.setLength(scoreLength);
        stringBuilder.append(model.getScore().getPontuation());
        TextColor.RGB backgroundColor = BackGroundColorFactory.getColor("Score:");
        TextColor.RGB foregroundColor = ForeGroundColorFactory.getColor("Score:");
        draw(stringBuilder.toString(), model.getScore().getPosition(), backgroundColor, foregroundColor);
    }

    private void drawHero(GameModel model) {
        TextColor.RGB backgroundColor = BackGroundColorFactory.getColor("✈");
        TextColor.RGB foregroundColor = ForeGroundColorFactory.getColor("✈");
        draw("✈", model.getHero().getPosition(), backgroundColor, foregroundColor);
    }

    private void drawExit(GameModel model) {
        TextColor.RGB backgroundColor = BackGroundColorFactory.getColor("✘");
        TextColor.RGB foregroundColor = ForeGroundColorFactory.getColor("✘");
        draw("✘", model.getExit().getPosition(), backgroundColor, foregroundColor);
    }

    private void drawWalls(GameModel model) {
        Set<Position> wallsPositions = model.getWallsPositions();
        TextColor.RGB backgroundColor = BackGroundColorFactory.getColor(" ");
        for (Position position : wallsPositions) {
            model.getWall().setPosition(position);
            draw(" ", position, backgroundColor, null);
        }
    }

    private void drawCoins(GameModel model) {
        Set<Position> coinPositions = model.getCoinPostions();
        TextColor.RGB backgroundColor = BackGroundColorFactory.getColor("o");
        TextColor.RGB foregroundColor = ForeGroundColorFactory.getColor("o");
        for (Position position : coinPositions) {
            model.getCoin().setPosition(position);
            draw("o", position, backgroundColor, foregroundColor);
        }
    }

    private void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();

            switch(character) {
                case 'w':
                    event.setType(Event.EVENT.UP);
                    break;
                case 'a':
                    event.setType(Event.EVENT.LEFT);
                    break;
                case 's':
                    event.setType(Event.EVENT.DOWN);
                    break;
                case 'd':
                    event.setType(Event.EVENT.RIGHT);
                    break;
                case 'q':
                    event.setType(Event.EVENT.CLOSE);
                    break;
                default:
                    event.setType(Event.EVENT.IGNORE);
                    break;
            }
        }

        if (event != null)
            GameState.notifyEvent(event);
    }

    public void readInput() throws IOException {
        KeyStroke key = screen.readInput();
        processKey(key);
    }




}
