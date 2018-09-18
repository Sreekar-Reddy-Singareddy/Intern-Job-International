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
import java.util.ArrayList;
import model.beans.Branch;

/**
 *
 * @author apple
 */
public class NationalInternshipService {
    
    private String responseString = "No Branches";
    
    /**
     * This method simply fetches all the branches from the
     * database and creates bean objects with them and returns
     * the JSON form of it to the caller
     * @param gson
     * @return 
     */
    public String getAllBranches (Gson gson) throws SQLException {
        // Make a connection to the database and start interaction
        Connection conn = MySQLConnection.getMySqlConnection();
        PreparedStatement query = conn.prepareStatement("SELECT B.BRANCH_ID, B.BRANCH_NAME, "
                + "(SELECT COUNT(POST_ID) OPP FROM COMPANY_POST CP WHERE CP.BRANCH_ID = B.BRANCH_ID AND STR_TO_DATE(ENDDATE, '%d/%m/%Y') >= CURDATE()) OPP_COUNT, "
                + "((SELECT COUNT(POST_ID) OPP FROM COMPANY_POST CP WHERE CP.BRANCH_ID = B.BRANCH_ID AND STR_TO_DATE(ENDDATE, '%d/%m/%Y') >= CURDATE()) > 0) FLAG "
                + "FROM BRANCHES B");
        ResultSet branches = query.executeQuery();
        
        // Iterate through the result set and create branch beans
        ArrayList<Branch> allBranches = new ArrayList<>();
        while (branches.next()) {
            // Create new branch bean with this row data
            Branch branch = new Branch();
            String branchName = branches.getString("BRANCH_NAME");
            int branchId = branches.getInt("BRANCH_ID");
            int opportunities = branches.getInt("OPP_COUNT");
            boolean isBranchOpen = branches.getBoolean("FLAG");
            
            branch.setId(branchId);
            branch.setIsOpen(isBranchOpen);
            branch.setName(branchName);
            branch.setNoOfOpportunities(opportunities);
            
            allBranches.add(branch);
        }
        
        System.out.println("All Branches: "+allBranches);
        
        return gson.toJson(allBranches);
    }
    
}
