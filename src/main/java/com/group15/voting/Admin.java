package com.group15.voting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Admin extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }

    public void openRegisterCandidates(MouseEvent mouseEvent) throws IOException {
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open new window
        RegisterCandidate registration = new RegisterCandidate();
        registration.start(stage);
    }

    public void openViewUsers(MouseEvent mouseEvent) throws IOException {
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open new window
        ViewUsers viewUsers = new ViewUsers();
        viewUsers.start(stage);
    }

    public void openViewCandidates(MouseEvent mouseEvent) throws IOException {
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open new window
        ViewCandidates viewCandidates = new ViewCandidates();
        viewCandidates.start(stage);

    }

    public void openViewResults(MouseEvent mouseEvent) {
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open new window
        ViewResults viewResults = new ViewResults();
        try {
            viewResults.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotohomepage(MouseEvent mouseEvent) throws IOException {
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open new window
        HomePage homepage = new HomePage();
        homepage.start(stage);
    }
}