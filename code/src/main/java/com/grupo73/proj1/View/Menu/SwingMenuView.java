package com.grupo73.proj1.View.Menu;

import com.grupo73.proj1.Controller.GameController;
import com.grupo73.proj1.MenuState;
import com.grupo73.proj1.Model.Menu.MenuModel;
import com.grupo73.proj1.StateObserver;
import com.grupo73.proj1.View.Event;
import com.grupo73.proj1.View.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SwingMenuView extends JPanel implements View<MenuModel> {
    private JFrame frame;
    private JButton start;
    private JButton exit;
    private Image slime;
    private MenuModel menuModel;

    public SwingMenuView() {
        StateObserver.frame.getContentPane().removeAll();
        frame = StateObserver.frame;
        setFocusable(true);
        this.setBounds(0,0,(GameController.ARENA_WIDTH - 1)*15, GameController.ARENA_HEIGHT*25);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        StateObserver.frame.getContentPane().add(this);
        StateObserver.frame.setVisible(true);
        StateObserver.frame.setBounds(0,0,(GameController.ARENA_WIDTH - 1)*15, GameController.ARENA_HEIGHT*25);
        loadImages();

        setUpButtons();
        addButtons();
        StateObserver.frame.revalidate();
        StateObserver.frame.repaint();
    }

    private void addButtons() {
        this.add(start);
        this.add(exit);
    }

    private void setUpButtons() {
        start = new JButton("Start Game");
        start.setBounds(300, 300, 200, 40);
        start.addActionListener( new ActionListener() {
            private Event event = new Event();

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                this.event.setType(Event.EVENT.ENTER);
                MenuState.notifyEvent(event);
            }
        });

        exit = new JButton("Exit Game");
        exit.setBounds(300, 350, 200, 40);

        exit.addActionListener( new ActionListener() {
            private Event event = new Event();

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                this.event.setType(Event.EVENT.CLOSE);
                MenuState.notifyEvent(event);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) { //função chamada por repaint()
        super.paintComponent(g);
        g.drawImage(slime, 285, 10, null);
    }

    @Override
    public void draw(MenuModel model) {
        repaint();
        this.menuModel = model;
    }

    private void loadImages() {
        URL resource = ClassLoader.getSystemResource("images/bigSlime.png");
        BufferedImage bfi = null;
        try {
           bfi = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon image = new ImageIcon(bfi);
        slime = image.getImage();
    }
}