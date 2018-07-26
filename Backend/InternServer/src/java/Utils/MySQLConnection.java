/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apple
 */
public class MySQLConnection {
    
    /**
     * This is a universal method to create 
     * connection with the MySQL server and return the
     * connection to the caller
     * @return 
     */
    public static Connection getMySqlConnection (){
        System.out.println("Getting Connection...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/interninternationdummy?useSSL=true", "root", "sreekar6");
            return conn;
        } catch (ClassNotFoundException ex) {
            System.out.println("SQL driver might be missing or -> "+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Some error in connecting to MySQL server. Check the URL and crendentials or -> "+ex.getMessage());
        } 
        return null;
    }
    
}
