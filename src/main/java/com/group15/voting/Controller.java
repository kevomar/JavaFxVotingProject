package com.group15.voting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Stage stage = new Stage();
    Scene scene;


    private String username;
    private String Uid;
    private Connect connect;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }

    public String getUid() {
        return Uid;
    }

    public String getUsername() {
        return username;
    }

    @FXML
    private String loginId;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBox<String> choiceBox = new ChoiceBox<String>();
        choiceBox.getItems().addAll("President", "WomenRep","Senator", "Governor","MP","MCA");
    }

    public void voteClick(ActionEvent actionEvent) throws IOException {
        //close current window
        stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        //open new window
        Login  login = new Login();
        login.start(stage);
    }


    public void resultsClick(ActionEvent actionEvent) throws IOException {
        //close current window
        stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        //open Results window
        Results results = new Results();
        results.start(stage);
    }

    public void exitClick(ActionEvent actionEvent) {
        stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


    public void openRegisterPage(ActionEvent mouseEvent) throws IOException {
        //open register.fxml
        //close current window
        stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open new window
        Registration registration = new Registration();
        registration.start(stage);
    }

    public void chngDetailsClick(ActionEvent actionEvent) {
    }
}