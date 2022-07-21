package com.group15.voting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Registration extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();
    }

    public void registerVoter(MouseEvent mouseEvent) throws SQLException, IOException {
        Scene scene = ((Node) mouseEvent.getSource()).getScene();
        //get firstname,lastname,id,Date of Birth,Password from register.fxml
        TextField firstnameField = (TextField) scene.lookup("#voterFirstName");
        TextField lastnameField = (TextField) scene.lookup("#voterLastName");
        TextField idField = (TextField) scene.lookup("#voterId");
        PasswordField passwordField = (PasswordField) scene.lookup("#voterPassword");
        PasswordField confirmPasswordField = (PasswordField) scene.lookup("#voterConfirmPassword");

        if(passwordField.getText().equals(confirmPasswordField.getText())){
            //Create an Object of Connect class
            Connect connect = new Connect();
            //Create a query to insert the user in the database
            String query = "INSERT INTO users (firstname, lastname, national_id, password ,status) VALUES ('" + firstnameField.getText() + "', '" + lastnameField.getText() + "', '" + idField.getText() + "', '" + passwordField.getText() + "' , '" + 0 + "')";
            //execute query
            connect.insert(query);
            //show seccess message
            JOptionPane.showMessageDialog(null, "Registered Successfully");

            //close current window
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
            //open new window
            Login  login = new Login();
            login.start(stage);
        }else{
            JOptionPane.showMessageDialog(null, "Passwords do not match");
        }
        //insert the data into the database
        //show confirmation dialog box
        //go to login.fxml
    }
}