package com.grupo73.proj1.Model.Menu;

import com.grupo73.proj1.Controller.CommandInput.Position;

import java.util.List;

public class MenuModel {
    private List<String> options;
    private Position position;
    private int selectedOption;

    public MenuModel(List<String> options, Position position) {
        this.options=options;
        this.position= position;
        this.selectedOption = 0;
    }

    public List<String> getOptions() {
        return options;
    }

    public Position getPosition() {
        return position;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void updateModelUp() {
        this.selectedOption = ((((this.selectedOption - 1 ) % options.size()) + options.size()) % options.size());

    }

    public void updateModelDown() {
        this.selectedOption = (this.selectedOption + 1 ) % (options.size());
    }
}
