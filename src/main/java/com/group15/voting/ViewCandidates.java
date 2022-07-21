package com.group15.voting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewCandidates extends Application implements Initializable {
    public TableView<Candidate> table;
    public TableColumn<Candidate, String> name;
    public TableColumn<Candidate, String> party;
    public TableColumn<Candidate, String> pos;
    public TableColumn<Candidate, String> id_no;
    public TableColumn<Candidate, Image> image;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("viewCandidates.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ViewCandidates");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        party.setCellValueFactory(new PropertyValueFactory<>("Party"));
        pos.setCellValueFactory(new PropertyValueFactory<>("Pos"));
        id_no.setCellValueFactory(new PropertyValueFactory<>("Id_no"));
        //add image to the last cell

        //get data from database
        Connect connect = new Connect();
        String sql = "SELECT firstname,lastname,party,position,id FROM candidates";
        try {
            ResultSet rs = connect.getData(sql);
            while (rs.next()) {
                String name = rs.getString("firstname") + " " + rs.getString("lastname");
                String party = rs.getString("party");
                String pos = rs.getString("position");
                String id_no = rs.getString("id");
                //ImageView image = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(rs.getString("image")))));

                table.getItems().add(new Candidate(name, party, pos, id_no));
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