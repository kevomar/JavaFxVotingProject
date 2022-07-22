package com.group15.voting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Login extends Application {
    private Scene scene;
    private Stage stage;
    private String Id;

    public String getId() {
        return Id;
    }


    public void setId(String id) {
        Id = id;
    }

    static boolean isLoggedin = false;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void onLogin(MouseEvent mouseEvent) throws IOException, SQLException {
        //get current scene
        scene = ((Node) mouseEvent.getSource()).getScene();
        //get idTextField and passwordTextField from login.fxml
        TextField usernameField = (TextField) scene.lookup("#idTextField");
        PasswordField passwordField = (PasswordField) scene.lookup("#passwordTextField");
        setId(usernameField.getText());
        //check if the user is an admin or a voter
        if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
            //open admin.fxml
            //close current window
            stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
            //open new window
            Admin  admin = new Admin();
            admin.start(stage);
        } else{
            //check if the user is in the database
            //Create an Object of Connect class
            Connect connect = new Connect();
            //Create a query to search for the user in the database
            String query = "SELECT * FROM users WHERE id = '" + usernameField.getText() + "'";
            //Execute the query
            //if yes, set the username and Uid variables
            String username = connect.getResults(query);
            //check if username is null
            if (username == null) {
                //if yes, display error message
                System.out.println("User not found");
            } else {
                //if no, set the Uid variable
                String Uid = connect.getUid(username);
                System.out.println(Uid);
                //check if the password is correct
                String password = passwordField.getText();
                String checkPassword = "SELECT password FROM users WHERE id = " + Uid;
                String res = connect.getPasswordResults(checkPassword);
                if(res.equals(password)){
                    //if yes, open voter.fxml
                    //close current window
                    stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();
                    stage.close();
                    Userdata.setUsername(Uid);
                    Userdata.setIsLoggedIn(true);
                    //open new window
                    Vote  vote = new Vote();
                    vote.start(stage);
                } else {
                    //if no, display error message
                    System.out.println("Password is incorrect");
                }
            }
        }
        //if no, show an error message

    }

    public void openRegisterPage(MouseEvent mouseEvent) throws IOException {
        //open register.fxml
        //close current window
        stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
        //open new window
        Registration registration = new Registration();
        registration.start(stage);
    }
}