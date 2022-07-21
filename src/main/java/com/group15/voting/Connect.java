package com.group15.voting;

import java.sql.*;

public class Connect {
    Connection conn;
    Statement statement;
    ResultSet resultSet;

    public Connect(){
        //open a db connection

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/oop";
            this.conn = DriverManager.getConnection(url, "root", "");
            this.statement = conn.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + "Connection Error");
        } catch (SQLException e) {
            System.out.println("Error: " + "SQL exception");
        }
    }

    public ResultSet select(String query){
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public String getResults(String query) throws SQLException {
        resultSet = select(query);
        String result = null;
        while(resultSet.next()){
            result = resultSet.getString("id");
        }
        return result;
    }

    public String getPasswordResults(String query) throws SQLException {
        resultSet = select(query);
        String result = null;
        while(resultSet.next()){
            result = resultSet.getString("password");
        }
        return result;
    }

    public String getUid(String username) {
        String query = "SELECT * FROM users WHERE id = '" + username + "'";
        String uid = null;
        try {
            uid = getResults(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return uid;
    }

    public String getPassword(String username) {
        String query = "SELECT password FROM users WHERE id = '" + username + "'";
        String password = null;
        try {
            password = getResults(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password;
    }

    public void insert(String query) throws SQLException {
        statement.execute(query);
    }

    public ResultSet getData(String query) throws SQLException {
        resultSet = select(query);
        return resultSet;
    }

    public void updateTally(String value, int tally) {
        String[] contain = value.split(" ");
        tally++;
        String query = "UPDATE candidates SET tally = " + tally + " WHERE firstname = '" + contain[0] + "' AND lastname = '" + contain[1] + "'";
        System.out.println(query);
        try {
            insert(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateData(String sql) {
        try {
            insert(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //create a destructor to close the connection
    /*public void finalize(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


}
