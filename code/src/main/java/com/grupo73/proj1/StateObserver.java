package com.grupo73.proj1;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import javax.swing.*;
import java.io.IOException;

public class StateObserver {
    private State state;
    private  MenuState menuState;
    private GameState gameState;
    public static Screen screen;
    public static JFrame frame;


    public StateObserver(String[] args) throws IOException {
        this.state = new State(this);
        if (args[0].equals("lanterna")) {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            menuState = new MenuState(state,args);
            gameState = new GameState(state,args);
            menuState.run();
        }
        else if (args[0].equals("swing")) {
            frame = new JFrame("Game");
            frame.setVisible(true);
            
            gameState = new GameState(state,args);
            gameState.run();
        }
        else {
            System.out.println("Input Error");
            System.out.println("Usage: ./gradlew run argument");
            System.out.println("Where argument = lanterna or argument = swing");
            System.exit(1);
        }



    }

    public void update() throws IOException {
        if (state.getState().equals(State.STATE.MENU)) {
            state.setState(State.STATE.GAME);
            gameState.run();
        }
        else {
            state.setState(State.STATE.MENU);
            menuState.run();
        }
    }
}
