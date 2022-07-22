package com.group15.voting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch();}
}