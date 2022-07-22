package com.group15.voting;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ViewResults extends Application implements Initializable {
    int choice = Results.choice;
    String query = Results.getQuery();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> Total = new ArrayList<>();
    @FXML
    PieChart piechart;
    @FXML
    Label declaration;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect connect = new Connect();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect.getData(query);
            while (resultSet.next()) {
                String fname = resultSet.getString("firstname");
                String lname = resultSet.getString("lastname");
                int votes = resultSet.getInt("tally");

                names.add(fname + " " + lname);
                Total.add(votes);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for(int i = 0; i < names.size(); i++) {
            pieChartData.add(new PieChart.Data(names.get(i), Total.get(i)));
        }
        piechart.setData(pieChartData);




    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("viewResults.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Results");
        stage.setScene(scene);
        stage.show();
    }


    public void view(MouseEvent mouseEvent) {
        //close the window
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        Results results = new Results();
        try {
            results.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}