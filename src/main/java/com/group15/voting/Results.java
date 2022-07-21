package com.group15.voting;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Results extends Application {
    public static int choice;
    public static String query;

    public TableView<ResultsView> table;
    public TableColumn<ResultsView, String> name;
    public TableColumn<ResultsView, String> position;
    public TableColumn<ResultsView, String> votes;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("result.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Results");
        stage.setScene(scene);
        stage.show();
    }


    public void openPview(MouseEvent mouseEvent) throws IOException {
        choice = 1;
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open viewResults.fxml
        ViewResults viewResults = new ViewResults();
        viewResults.start(stage);
    }

    public void openMpView(MouseEvent mouseEvent) throws IOException {
        choice = 2;
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open viewResults.fxml
        ViewResults viewResults = new ViewResults();
        viewResults.start(stage);
    }

    public void opengview(MouseEvent mouseEvent) throws IOException {
        choice = 3;
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open viewResults.fxml
        ViewResults viewResults = new ViewResults();
        viewResults.start(stage);
    }

    public void openmcview(MouseEvent mouseEvent) {
        choice = 6;
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open viewResults.fxml
        ViewResults viewResults = new ViewResults();
        try {
            viewResults.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openwview(MouseEvent mouseEvent) {
        choice = 5;
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open viewResults.fxml
        ViewResults viewResults = new ViewResults();
        try {
            viewResults.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opensview(MouseEvent mouseEvent) {
        choice = 4;
        //close current window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open viewResults.fxml
        ViewResults viewResults = new ViewResults();
        try {
            viewResults.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getQuery() {
        if (choice == 1) {
            query = "SELECT * FROM candidates WHERE position = 'president'";
        } else if (choice == 2) {
            query = "SELECT * FROM candidates WHERE position = 'MP'";
        } else if (choice == 3) {
            query = "SELECT * FROM candidates WHERE position = 'Governor'";
        } else if (choice == 4) {
            query = "SELECT * FROM candidates WHERE position = 'Senator'";
        } else if (choice == 5) {
            query = "SELECT * FROM candidates WHERE position = 'womenRep'";
        } else if (choice == 6) {
            query = "SELECT * FROM candidates WHERE position = 'MCA'";
        }
        return query;
    }
}