/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import Utils.MySQLConnection;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.beans.Login;

/**
 *
 * @author apple
 */
public class LoginService {
    
    private String responseString = "NA";
    
    /**
     * This method accepts only one parameter - request data
     * that was converted into JSON format string.
     * This string is used to create various java bean objects
     * @param reqDataInJsonString 
     * @param gson : used to work across JSON string and java objects
     */
    public String serveRequestWithData (String reqDataInJsonString, Gson gson) throws SQLException {
        System.out.println("Inside login service: "+ reqDataInJsonString);
        
        // Convert the JSON string into Java bean and use the data there after;
        Login currentUserToVerify = gson.fromJson(reqDataInJsonString, Login.class);
        
        // Make query to the database and get the required result
        Connection conn = MySQLConnection.getMySqlConnection();
        PreparedStatement sqlQuery = conn.prepareStatement("SELECT NAME, EMAIL, NRML_PWD FROM USERS WHERE EMAIL = ? AND NRML_PWD = ?");
        sqlQuery.setString(1, currentUserToVerify.getEmail());
        sqlQuery.setString(2, currentUserToVerify.getPassword());
        ResultSet resultSet = sqlQuery.executeQuery();
        
        // Check the result set and take apt actions
        // If user exists, there has to be exactly 1 row
        boolean userExists = false;
        Login loggedInUser = null;
        while (resultSet.next()) {
            loggedInUser = new Login();
            loggedInUser.setEmail(resultSet.getString("EMAIL"));
            loggedInUser.setName(resultSet.getString("NAME"));
            loggedInUser.setPassword(resultSet.getString("NRML_PWD"));
            userExists = true;
        }
        
        // Convert the java object into JSON string and return to the caller
        // By default the resposne string will have 'NA' unless there is an user
        if (userExists && loggedInUser != null) {
            responseString = gson.toJson(loggedInUser);
            return responseString;
        }
        return responseString;
    }
    
}
