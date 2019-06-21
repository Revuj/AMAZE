package com.grupo73.proj1;

import com.grupo73.proj1.Controller.GameController;
import com.grupo73.proj1.Model.GameModel;
import com.grupo73.proj1.View.Event;
import com.grupo73.proj1.View.Game.LanternaGameView;
import com.grupo73.proj1.View.Game.SwingGameView;

import java.io.IOException;

public class GameState {

    private static State state;
    private static String mode;
    private static boolean gameRunning;
    private static GameModel model;


        private static GameController controller = new GameController();

        public GameState(State state, String [] args) {
            this.state = state;
            mode = args[0];
            try {
                model = new GameModel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void notifyEvent(Event event) {
            if (event.getType() == null)
                return;

            if (event.getType().equals(Event.EVENT.CLOSE)) {
                gameRunning = false;
                state.notifyObserver();
                return;
            }

            controller.consumeEvent(event, model);
        }

        public static void run() throws IOException {

            gameRunning = true;
            if (mode.equals("swing")) {

                SwingGameView view = null;
                try {
                    view = new SwingGameView();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                SwingGameView finalView = view;

                final int FPS = 60;
                final int UPS = 60;
                long initialTime = System.nanoTime();
                final double timeF = 1000000000 / FPS;
                final double timeU = 1000000000 / UPS;
                double deltaU = 0, deltaF = 0;

                while (gameRunning) {
                    long currentTime = System.nanoTime();
                    deltaU += (currentTime - initialTime) / timeU;
                    deltaF += (currentTime - initialTime) / timeF;
                    initialTime = currentTime;

                    if (deltaU >= 1) {
                        controller.getHero().move(model);
                        deltaU--;
                    }

                    if (deltaF >= 1) {
                        finalView.draw(model);
                        deltaF--;
                    }

                }
            }
            else if (mode.equals("lanterna")) {
                LanternaGameView view = null;
                view = new LanternaGameView();

                LanternaGameView finalView = view;

                new Thread(() -> {
                    Event ev = null;
                    while (gameRunning) {
                        try {
                            finalView.readInput();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                final int FPS = 60;
                final int UPS = 60;
                long initialTime = System.nanoTime();
                final double timeF = 1000000000 / FPS;
                final double timeU = 1000000000 / UPS;
                double deltaU = 0, deltaF = 0;

                while (gameRunning) {

                    long currentTime = System.nanoTime();
                    deltaU += (currentTime - initialTime) / timeU;
                    deltaF += (currentTime - initialTime) / timeF;
                    initialTime = currentTime;

                    if (deltaU >= 1) {
                        controller.getHero().move(model);
                        deltaU--;
                    }

                    if (deltaF >= 1) {
                        finalView.draw(model);
                        deltaF--;
                    }

                }
            }
        }
}
