module com.group15.voting {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.java;


    opens com.group15.voting to javafx.fxml;
    exports com.group15.voting;
}