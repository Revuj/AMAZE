package com.grupo73.proj1.Model.Entities;

public class ScoreModel extends EntityModel {

    private int pontuation;
    public ScoreModel(int x, int y) {
        super(x, y);
        pontuation = 0;
    }

    public int getPontuation() {
        return pontuation;
    }

    public void addPoints(int points) {
        pontuation += points;
    }
}
