package com.grupo73.proj1.View;

public class  Event {
    public enum EVENT {UP, DOWN, LEFT, RIGHT, CLOSE, ENTER, IGNORE}

    private EVENT type;

    public EVENT getType() {
        return type;
    }

    public void setType(EVENT type) {
        this.type = type;
    }
}
