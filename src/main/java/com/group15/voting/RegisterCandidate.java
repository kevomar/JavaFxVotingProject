package com.group15.voting;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterCandidate extends Application implements Initializable {

    @FXML
    private ChoiceBox<String> cpos;

    String[] positions = {"President", "WomenRep","Senator", "Governor","MP","MCA"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //link choice box to cpos in registerCandidate.fxml
        cpos.getItems().addAll(positions);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("registerCandidate.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RegisterCandidate");
        stage.setScene(scene);
        stage.show();
    }

    public void submitCandidate(MouseEvent mouseEvent) throws SQLException, IOException {
        //get the text from the text fields
        Scene scene = ((Node) mouseEvent.getSource()).getScene();
        TextField firstnameField = (TextField) scene.lookup("#cfirstname");
        TextField lastnameField = (TextField) scene.lookup("#clastname");
        TextField idField = (TextField) scene.lookup("#cid");
        TextField partyField = (TextField) scene.lookup("#cparty");
        TextField imageField = (TextField) scene.lookup("#imagePath");
        //Image image = new Image(imageField.getText());
        //get value from choice-box
        String position = cpos.getValue();

        //insert into database
        Connect connect = new Connect();
        String query = "INSERT INTO candidates (firstname, lastname, id, party, position,tally) VALUES ('" + firstnameField.getText() + "', '" + lastnameField.getText() + "', '" + idField.getText() + "', '" + partyField.getText() + "', '" + position + "', '" + 0 +"')";
        connect.insert(query);

        //Display successful insertion message
        JOptionPane.showMessageDialog(null, "Candidate successfully registered");
        //close current window
        Stage stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open admin window
        Admin admin = new Admin();
        admin.start(stage);
    }

    public void chooseImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            /*set the image path to the image path text field
            Scene scene = ((Node) mouseEvent.getSource()).getScene();
            TextField imagePathField = (TextField) scene.lookup("#imagePath");
            String path = selectedFile.getAbsolutePath().toString();
            imagePathField.setText(path);*/
            try {
                FileInputStream fis = new FileInputStream(selectedFile);
                Image image = new Image(fis);
                Scene scene = ((Node) mouseEvent.getSource()).getScene();
                TextField imagePathField = (TextField) scene.lookup("#imagePath");
                imagePathField.setText(selectedFile.getAbsolutePath().toString());

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            //display error message
            JOptionPane.showMessageDialog(null, "Please select an image");
        }
    }

}