package com.grupo73.proj1.View.Game;

import com.grupo73.proj1.Controller.CommandInput.Position;
import com.grupo73.proj1.Controller.GameController;
import com.grupo73.proj1.GameState;
import com.grupo73.proj1.Model.GameModel;
import com.grupo73.proj1.StateObserver;
import com.grupo73.proj1.View.Event;
import com.grupo73.proj1.View.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class SwingGameView extends JPanel implements ActionListener, View<GameModel> {
    private JFrame frame;
    private com.grupo73.proj1.View.Event event;
    private Image coin;
    private Image hero;
    private Image portal;
    private Image wall;
    private Image exit;
    private GameModel gameModel;

    public SwingGameView() throws IOException {
        StateObserver.frame.getContentPane().removeAll();
        StateObserver.frame.getContentPane().add(this);
        StateObserver.frame.revalidate();
        StateObserver.frame.repaint();

        frame = StateObserver.frame;
        addKeyListener(new MyKeyboardAdapter());
        setFocusable(true);

        this.setBounds(0,0,(GameController.ARENA_WIDTH - 1)*15, GameController.ARENA_HEIGHT*25);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        StateObserver.frame.setVisible(true);
        StateObserver.frame.setBounds(0,0,(GameController.ARENA_WIDTH - 1)*15, GameController.ARENA_HEIGHT*25);
        event = new com.grupo73.proj1.View.Event();
        loadImages();

    }

    public void loadImages() {
        URL resource = ClassLoader.getSystemResource("images/coin.jpg");
        BufferedImage bfi = null;
        try {
            bfi = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon image = new ImageIcon(bfi);
        coin = image.getImage();

        resource = ClassLoader.getSystemResource("images/door.png");
        try {
            bfi = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new ImageIcon(bfi);
        exit = image.getImage();


        resource = ClassLoader.getSystemResource("images/slime.png");
        try {
            bfi = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new ImageIcon(bfi);
        hero = image.getImage();


        resource = ClassLoader.getSystemResource("images/portal.png");
        try {
            bfi = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new ImageIcon(bfi);
        portal = image.getImage();


        resource = ClassLoader.getSystemResource("images/wall.jpg");
        try {
            bfi = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new ImageIcon(bfi);
        wall = image.getImage();

    }

    @Override
    public void paintComponent(Graphics g) { //função chamada por repaint()
        super.paintComponent(g);
        drawWalls(g);
        drawPortals(g);
        drawCoins(g);
        drawExit(g);
        drawHero(g);
    }



    @Override
    public void draw(GameModel model) throws IOException {
        repaint();
        this.gameModel = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private class MyKeyboardAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_W:
                    event.setType(com.grupo73.proj1.View.Event.EVENT.UP);
                    break;
                case KeyEvent.VK_A:
                    event.setType(com.grupo73.proj1.View.Event.EVENT.LEFT);
                    break;
                case KeyEvent.VK_S:
                    event.setType(com.grupo73.proj1.View.Event.EVENT.DOWN);
                    break;
                case KeyEvent.VK_D:
                    event.setType(com.grupo73.proj1.View.Event.EVENT.RIGHT);
                    break;
                case KeyEvent.VK_Q:
                    event.setType(com.grupo73.proj1.View.Event.EVENT.CLOSE);
                    break;
                default:
                    event.setType(Event.EVENT.IGNORE);
                    break;
            }

            GameState.notifyEvent(event);
        }
    }

    private void drawPortals(Graphics g) {
        for(Position position : gameModel.getPortalsPosition()) {
            g.drawImage(portal, position.getX() * 15, position.getY() * 25, null);
        }
    }

    private void drawHero(Graphics g) {
        g.drawImage(hero, gameModel.getHero().getPosition().getX() * 15, gameModel.getHero().getPosition().getY() * 25, null);
    }

    private void drawExit(Graphics g) {
        g.drawImage(exit, gameModel.getExit().getPosition().getX() * 15, gameModel.getExit().getPosition().getY() * 25, null);

    }

    private void drawWalls(Graphics g) {
        Set<Position> wallsPositions = gameModel.getWallsPositions();
        for (Position position : wallsPositions) {
            g.drawImage(wall, position.getX() * 15, position.getY() * 25, null);
        }
    }

    private void drawCoins(Graphics g) {
        Set<Position> coinPositions = gameModel.getCoinPostions();
        for (Position position : coinPositions) {
            g.drawImage(coin, position.getX() * 15, position.getY() * 25, null);
        }
    }
}
