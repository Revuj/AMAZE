package com.grupo73.proj1;

import com.grupo73.proj1.Controller.CommandInput.Position;
import com.grupo73.proj1.Controller.MenuController;
import com.grupo73.proj1.Model.Menu.MenuModel;
import com.grupo73.proj1.View.Event;
import com.grupo73.proj1.View.Menu.LanternaMenuView;
import com.grupo73.proj1.View.Menu.SwingMenuView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuState {
    private static State state;
    private static String mode;
    private static boolean menuRunning = true;
    private static MenuModel model;
    private static SwingMenuView viewSwing;
    private static LanternaMenuView viewLanterna;
    private static MenuController controller;

    public MenuState(State state, String [] args) throws IOException {
        this.state = state;
        List<String> options = new ArrayList<>();
        options.add("Play");
        options.add("Exit");
        mode = args[0];
        if (mode.equals("lanterna"))
            viewLanterna = new LanternaMenuView();
        else if (mode.equals("swing"))
            viewSwing= new SwingMenuView();

        model = new MenuModel(options, new Position(5,5));
        controller = new MenuController();
    }

    public static void notifyEvent(Event event) {

        if (event.getType() == null) {
            return;
        }
        if (event.getType().equals(Event.EVENT.CLOSE)) {
            menuRunning = false;
            System.exit(0);
        }

        if (event.getType().equals(Event.EVENT.ENTER)) {
            menuRunning = false;

            if (model.getSelectedOption() == 1)
                System.exit(0);

            state.notifyObserver();
        }

        if (event != null)
            controller.consumeEvent(event, model);
    }


    public static void run() throws IOException {
        menuRunning = true;

        if (mode.equals("swing")) {
            final int FPS = 60;
            final int UPS = 60;
            long initialTime = System.nanoTime();
            final double timeF = 1000000000 / FPS;
            final double timeU = 1000000000 / UPS;
            double deltaU = 0, deltaF = 0;

            while (menuRunning) {

                long currentTime = System.nanoTime();
                deltaU += (currentTime - initialTime) / timeU;
                deltaF += (currentTime - initialTime) / timeF;
                initialTime = currentTime;

                if (deltaF >= 1) {
                    viewSwing.draw(model);
                    deltaF--;
                }

            }
        }
        else if (mode.equals("lanterna")){

            new Thread(() -> {
                Event ev = null;
                while (menuRunning) {
                    try {
                        viewLanterna.readInput();
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

            while (menuRunning) {

                long currentTime = System.nanoTime();
                deltaU += (currentTime - initialTime) / timeU;
                deltaF += (currentTime - initialTime) / timeF;
                initialTime = currentTime;


                if (deltaF >= 1) {
                    viewLanterna.draw(model);
                    deltaF--;
                }

            }
        }
    }

}
