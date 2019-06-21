package com.grupo73.proj1.Model;


import com.grupo73.proj1.Controller.CommandInput.Position;
import com.grupo73.proj1.Controller.GameController;
import com.grupo73.proj1.Model.Entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameModel {

    private HeroModel hero;
    private ExitModel exit;
    private ScoreModel score;
    private Set<Position> wallsPositions;
    private Set<Position> coinPostions;
    private List<Position> portalsPosition;
    private WallModel wall = new WallModel(0,0);
    private CoinModel coin = new CoinModel(0,0);
    private PortalModel portal = new PortalModel(0,0);


    enum entities { WALL, EXIT, HERO, COIN, PORTAL}

    private final int ASCII_CODE_WALL = 35;
    private final int ASCII_CODE_EXIT = 83;
    private final int ASCII_CODE_HERO = 80;
    private final int ASCII_CODE_COIN = 67;
    private final int ASCII_CODE_PORTAL = 84;

    private static int currentLevel = 1;
    private final static int TOTAL_OF_LEVELS = 4;


    public GameModel() throws IOException {
        this.wallsPositions = new HashSet<>();
        this.coinPostions = new HashSet<>();
        this.portalsPosition = new ArrayList<>();
        this.score = new ScoreModel(1,20);
        loadLevel(currentLevel);

    }

    public void loadLevel(int levelNo) throws IOException {
        URL resource = ClassLoader.getSystemResource("rooms/lvl" + levelNo + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        createLevelMap(br);
    }

    private entities checkEntities(int asciiCode) {

        switch (asciiCode) {
            case ASCII_CODE_WALL:
                return entities.WALL;
            case ASCII_CODE_EXIT:
                return entities.EXIT;
            case ASCII_CODE_HERO:
                return entities.HERO;
            case ASCII_CODE_COIN:
                return entities.COIN;
            case ASCII_CODE_PORTAL:
                return entities.PORTAL;
            default: break;
        }

        return null;
    }

    private boolean checkNewLine(int asciiCode) {
        return asciiCode == 10;
    }

    private void createLevelMap(BufferedReader br) throws IOException {
        int r;
        int j = 0;
        for (int i = 0; (r = br.read()) != -1; i++) {
            if (checkEntities(r) == entities.WALL) {
                wallsPositions.add(new Position(i % GameController.ARENA_WIDTH, j % GameController.ARENA_HEIGHT));
            }
            else if (checkEntities(r) == entities.EXIT)
                exit = new ExitModel(i%GameController.ARENA_WIDTH, j%GameController.ARENA_HEIGHT);
            else if (checkEntities(r) == entities.HERO)
                hero = new HeroModel(i%GameController.ARENA_WIDTH, j%GameController.ARENA_HEIGHT);
            else if (checkEntities(r) == entities.COIN)
                coinPostions.add(new Position(i % GameController.ARENA_WIDTH, j % GameController.ARENA_HEIGHT));
            else if (checkEntities(r) == entities.PORTAL)
                portalsPosition.add(new Position(i % GameController.ARENA_WIDTH, j % GameController.ARENA_HEIGHT));
            else if (checkNewLine(r))
                j++;

        }
    }


    public HeroModel getHero() {
        return hero;
    }

    private boolean isNotLastLevel() {
        return currentLevel < TOTAL_OF_LEVELS;
    }

    private void updateLevelNr() {
        if(isNotLastLevel())
            currentLevel++;
    }

    public Set<Position> getWallsPositions() {
        return this.wallsPositions;
    }

    public List<Position> getPortalsPosition() {
        return portalsPosition;
    }

    public Set<Position> getCoinPostions() {
        return coinPostions;
    }

    public ExitModel getExit() {
        return exit;
    }

    public int getLevel() {
        return currentLevel;
    }


    public void updateGameModel() throws IOException {
        this.updateLevelNr();
        this.wallsPositions.clear();
        this.coinPostions.clear();
        loadLevel(currentLevel);
        this.score.addPoints(500);
    }

    public ScoreModel getScore() {
        return score;
    }

    public void updateCoins(Position position) {

        this.coinPostions.remove(position);
        this.score.addPoints(50);
    }

    public PortalModel getPortal() {
        return portal;
    }

    public CoinModel getCoin() {
        return coin;
    }

    public WallModel getWall() {
        return wall;
    }
}