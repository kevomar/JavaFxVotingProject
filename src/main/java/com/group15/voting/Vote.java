package com.group15.voting;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Vote extends Application implements Initializable {
    @FXML
    public ChoiceBox<String> president;
    @FXML
    public ChoiceBox<String> governor;
    @FXML
    public ChoiceBox<String> senator;
    @FXML
    public ChoiceBox<String> womenrep;
    @FXML
    public ChoiceBox<String> mp;
    @FXML
    public ChoiceBox<String> mca;
    @FXML
    public Button submit;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("vote.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Vote");
        stage.setScene(scene);
        stage.show();
    }

    public void votervote(MouseEvent mouseEvent) throws SQLException {
        //get choicebox values
        String presidentValue = president.getValue();
        String governorValue = governor.getValue();
        String senatorValue = senator.getValue();
        String womenrepValue = womenrep.getValue();
        String mpValue = mp.getValue();
        String mcaValue = mca.getValue();

        System.out.println(presidentValue);
        //get tally from database
        int presidentTally = getTally(presidentValue);
        int governorTally = getTally(governorValue);
        int senatorTally = getTally(senatorValue);
        int womenrepTally = getTally(womenrepValue);
        int mpTally = getTally(mpValue);
        int mcaTally = getTally(mcaValue);

        //update tally in database
        Connect connect = new Connect();
        //check if status = 1
        String query = "SELECT * FROM users WHERE id = '" + Userdata.getUsername() + "'";
        ResultSet resultSet = connect.getData(query);
        while(resultSet.next()){
            int status = resultSet.getInt("status");
            if(status == 1){
                System.out.println("User has voted");
                JOptionPane.showMessageDialog(null, "You have already voted");
            }else{
                connect.updateTally(presidentValue, presidentTally);
                connect.updateTally(governorValue, governorTally);
                connect.updateTally(senatorValue, senatorTally);
                connect.updateTally(womenrepValue, womenrepTally);
                connect.updateTally(mpValue, mpTally);
                connect.updateTally(mcaValue, mcaTally);

                //Show confirmation
                JOptionPane.showMessageDialog(null, "Vote casted successfully");

                //go to results page
                //update voter status
                String sql = "UPDATE users SET status = 1 WHERE id = '"+ Userdata.getUsername()+"'";
                connect.updateData(sql);
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillPositions("President", president);
        fillPositions("Governor", governor);
        fillPositions("Senator", senator);
        fillPositions("WomenRep", womenrep);
        fillPositions("MP", mp);
        fillPositions("MCA", mca);
    }

    public void fillPositions(String position, ChoiceBox<String> choiceBox) {
        //Conect to database
        Connect connect = new Connect();
        //Get the list of  presidential candidates
        String sql = "SELECT * FROM candidates WHERE position = '" + position + "'";
        try {
            ResultSet rs = connect.getData(sql);
            while (rs.next()) {
                choiceBox.getItems().add(rs.getString("firstname") + " " + rs.getString("lastname"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTally(String name){
        String[] contain = name.split(" ");
        Connect connect = new Connect();
        String sql = "SELECT * FROM candidates WHERE firstname = '" + contain[0] + "' AND lastname = '" + contain[1] + "'";
        try {
            ResultSet rs = connect.getData(sql);
            while (rs.next()) {
                return rs.getInt("tally");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}