package com.group15.voting;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ResultsView {
    private SimpleStringProperty name;
    private SimpleStringProperty position;
    private SimpleIntegerProperty tally;

    public ResultsView(String name, String position, int tally) {
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.tally = new SimpleIntegerProperty(tally);
    }

    public String getName() {
        return name.get();
    }

    public String getPosition() {
        return position.get();
    }

    public int getTally() {
        return tally.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setPosition(String position) {
        this.position = new SimpleStringProperty(position);
    }

    public void setTally(int tally) {
        this.tally = new SimpleIntegerProperty(tally);
    }
}
