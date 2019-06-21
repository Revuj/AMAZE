package com.grupo73.proj1.Model.Entities;


public class HeroModel extends EntityModel {

    private boolean portalsEnabled;


    public HeroModel(int x, int y) {
        super(x,y);
        this.portalsEnabled = true;
    }

    public boolean getPortalsEnabled() {
        return this.portalsEnabled;
    }

    public void justUsedPortal() {
        this.portalsEnabled = true;
    }

    public void disablePortals() {
        this.portalsEnabled = false;
    }

}
