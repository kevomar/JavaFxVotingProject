package com.group15.voting;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class Candidate {
    private SimpleStringProperty name;
    private SimpleStringProperty party;
    private SimpleStringProperty pos;
    private SimpleStringProperty id_no;
    private ImageView image;

    public Candidate(String name, String party, String pos, String id_no) {
        this.name = new SimpleStringProperty(name);
        this.party = new SimpleStringProperty(party);
        this.pos = new SimpleStringProperty(pos);
        this.id_no = new SimpleStringProperty(id_no);
        this.image = image;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getParty() {
        return party.get();
    }

    public void setParty(String party) {
        this.party = new SimpleStringProperty(party);
    }

    public String getPos() {
        return pos.get();
    }

    public void setPos(String pos) {
        this.pos = new SimpleStringProperty(pos);
    }

    public String getId_no() {
        return id_no.get();
    }

    public void setId_no(String id_no) {
        this.id_no = new SimpleStringProperty(id_no);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = new ImageView(image);
    }
}
