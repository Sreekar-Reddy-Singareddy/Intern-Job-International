// This module has only one function
// The function is used to create and return a DB connection
// to the given credentials
var mysql = require("mysql");

/*
 Properties of the database server
 */
var hostname = "localhost";
var username = "root";
var password = "sreekar6";
var database = "interninternationdummy";
var connection;

/*
 This function creates the connection to the database with above credits
 and returns it to the caller
 */
var dbConnector = function () {
    var dbConn = mysql.createConnection ({
                            host: hostname,
                            user: username,
                            password: password,
                            database: database
    });
    
    // Check if the connection is success or failure
    dbConn.connect(function (err) {
                   if (err) {
                   console.log("Connection to server failed: "+err.message);
                   return 123;
                   }
                   console.log("Connected to server: "+hostname);
                   connection = dbConn;
                   });
    
    // Return the established connection to the caller
    return dbConn;
};

// Export the function to other modules
module.exports = dbConnector;
