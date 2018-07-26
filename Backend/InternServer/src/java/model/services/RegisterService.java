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
import model.beans.Register;

/**
 *
 * @author apple
 */
public class RegisterService {
    private String responseString = "Insert Fail";
    
    /**
     * This method accepts only one parameter - request data
     * that was converted into JSON format string.
     * This string is used to create various java bean objects
     * @param reqDataInJsonString 
     * @param gson : used to work across JSON string and java objects
     */
    public String serveRequestWithData (String reqDataInJsonString, Gson gson) throws SQLException {
        System.out.println("Inside register service: "+ reqDataInJsonString);
        
        // Convert the JSON String into java bean
        Register userToBeRegistered = gson.fromJson(reqDataInJsonString, Register.class);
        Connection conn = MySQLConnection.getMySqlConnection();
        
        // All the queries to be made
        String duplicateUserQuery = "SELECT COUNT(EMAIL) AS DUP_USERS FROM USERS WHERE EMAIL = ? OR MOBILE = ?";
        String insertUserQuery = "INSERT INTO USERS (NAME, EMAIL, MOBILE, NRML_PWD, STUDENT_ID, PROFILE_CREATED, ROLE, ACTIVE) "
                + "VALUES (?, ?, ?, ?, ?, CONVERT(DATE_FORMAT(CURDATE(), '%d-%c-%Y'), CHAR), 2, 0)";
        
        // Check for duplicate user first
        PreparedStatement sqlQuery = conn.prepareStatement(duplicateUserQuery);
        sqlQuery.setString(1, userToBeRegistered.getEmail());
        sqlQuery.setString(2, userToBeRegistered.getMobile());
        ResultSet resultSet = sqlQuery.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getInt("DUP_USERS") > 0) {
                // This means there are users with the same credentials already existing
                responseString = "User Exists";
                return responseString;
            }
        }
        
        // Insert the new user if not existing already
        // Build Student ID: ST + (6-lenOfId)0 + allUsers
        // ST000123, ST001234
        sqlQuery = conn.prepareStatement("SELECT MAX(USER_ID) ALL_USERS FROM USERS");
        resultSet = sqlQuery.executeQuery();
        resultSet.next();
        Integer allUsers = resultSet.getInt("ALL_USERS") + 1;
        int lenOfId = allUsers.toString().length();
        char [] zeros = new char [] {'0','0','0','0','0','0'};
        StringBuilder studentId = new StringBuilder("");
        studentId.append("ST");
        studentId.append(zeros, 0, 6-lenOfId);
        studentId.append(allUsers);
        
        sqlQuery = conn.prepareStatement(insertUserQuery);
        sqlQuery.setString(1, userToBeRegistered.getName());
        sqlQuery.setString(2, userToBeRegistered.getEmail());
        sqlQuery.setString(3, userToBeRegistered.getMobile());
        sqlQuery.setString(4, userToBeRegistered.getPassword());
        sqlQuery.setString(5, studentId.toString());
        if (sqlQuery.executeUpdate() == 1) {
            // This means the new user with these credentials is inserted in table
            responseString = "Insert Success";
            return responseString;
        }
        
        return responseString;
    }
}
