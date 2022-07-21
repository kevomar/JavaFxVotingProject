package com.group15.voting;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewUsers extends Application implements Initializable {
    @FXML
    public TableView<Users> table;
    @FXML
    public TableColumn<Users, String> firstname;
    @FXML
    public TableColumn<Users, String> lastname;
    @FXML
    public TableColumn<Users, String> id;
    @FXML
    public TableColumn<Users, String> status;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("viewUsers.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("view Users");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstname.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        status.setCellValueFactory(new PropertyValueFactory<>("Status"));

        //get data from database
        Connect connect = new Connect();
        String sql = "SELECT firstname,lastname,id,status FROM users";
        try {
            ResultSet rs  = connect.getData(sql);
            while(rs.next()){
                int stat = rs.getInt("status");
                String see;
                if(stat == 1){
                    see = "Voted";
                }else{
                    see = "Not Voted";
                }
                table.getItems().add(new Users(rs.getString("firstname"),rs.getString("lastname"),rs.getString("id"),see));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void goback(MouseEvent mouseEvent) throws IOException {
        //close current window
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        //open admin page
        Admin admin = new Admin();
        admin.start(new Stage());
    }
}