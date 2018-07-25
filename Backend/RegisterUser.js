// We need to use some modules, hence import them
var http = require("http");
var mysql = require("mysql");
var url = require("url");
var qs = require("querystring");
var sss = require("./ParseRequest.js");

// All variables
// Http server variables
var httpConnection;
var port = 8085;

// Server Data related variables
var rawReqData = "";

// SQL variables
var sqlQuery;
var rowsInserted;
var stringResultUserId;
var jsonUserId;
var userId;
var studentId;
var today = new Date();
var registeredDate;
var role = 2;
var active = 0;
var duplicateUserFlag = false;
var inputUsername = "";
var inputPassword = "";

var dbConn;
var requestURL;
var requestURLPath;
var requestDataJson = "";

// Create a HTTP server which listens to client requests
// URL format - http://<hostname>:<port>/<taskname>
// taskname - login, register, and others
httpConnection = http.createServer(function (req, res) {
                  // Break the URL and check for the taskname
                  requestURL = req.url;
                  
                  // Get DB Connection
                  dbConn = getDbConnection();

                  // Get the request data into JS object
                  requestDataJson = getRequestData(req);

                  // Connect to the MySql server
                  dbConn.connect(function (err) {
                                 // Check if the there is error connecting
                                 if (err) {
                                 throw err;
                                 } // end if
                                 // No error, so start DB interaction
                                 queryInternshipDatabase(dbConn, requestURL, res);
                                 }); // end connect()
                  
                  // End the connection by ending the response
                  
                  }).listen(port); // end createServer()

// Function that returns Connection to MySQL server
function getDbConnection() {
    console.log("Inside getDbConnection");
    var tempConn = mysql.createConnection({
                                          host:"localhost",
                                          user:"root",
                                          password:"sreekar6",
                                          database:"interninternationdummy"
    });
    return tempConn;
}

// Take request object and return the data in it in the form of JS object
function getRequestData(request) {
    console.log("Inside getRequestData");
    if (request.method == "POST") {
        // Read the data from the client request
        request.on("data", function(data) {
                   console.log("Inside data function",data);
                   rawReqData += data;
                   console.log("Inside data function",rawReqData);
               }); // end req.on()
        
        // Parse the data into JS object
        request.on("end", function() {
                   console.log("In end function");
                   requestDataJson = qs.parse(rawReqData,"#","=");
                   console.log("JSON Data:::::: ",requestDataJson);
               }); // end req.on()
        // TODO: Return the read data here
    } // end if
    return null;
}

// Based on the task name, the db is queried
function queryInternshipDatabase(dbConn, task, httpResponse) {
    console.log("Inside queryInternshipDatabase");
    console.log("Request Data: ",requestDataJson);
    if (task == "/loginuser") {
        // Query for fetching user
        inputUsername = requestDataJson.email;
        inputPassword = requestDataJson.nrml_pwd;
        var sqlQuery = "SELECT name,email,nrml_pwd FROM users WHERE email=? AND nrml_pwd=?";
        console.log("SQL Query: "+sqlQuery);
        dbConn.query(sqlQuery,[inputUsername,inputPassword],function(err,result) {
                  if (err) throw err;
                  console.log("Result: ",result.length);
                  var jsonData = JSON.stringify(result);
                  var jsonArray = JSON.parse(jsonData);
                  httpResponse.writeHead(200, {'Content-Type': 'text/plain'});
                  // Write the user that match the query
                  // 0 - If user does not exist
                  // 1 - If user exists
                  if (result.length == 1) {
                  console.log("JSON Result: ",jsonArray[0]);
                  httpResponse.write(JSON.stringify(jsonArray[0]));
                  }
                  else {
                  httpResponse.write("");
                  }
                     httpResponse.end();
                  });
        rawReqData = "";
    }
    else if (task == "/registeruser") {
        // Checking for duplicte user detailso
        dupUserQuery = "SELECT USER_ID FROM USERS WHERE (MOBILE = ?) OR (EMAIL = ?)";
        dbConn.query(dupUserQuery, [requestDataJson.mobile, requestDataJson.email], function(err, result) {
                     if (err) {
                     httpResponse.write("Insert Fail");
                     throw err;
                     }
                     if (result.length != 0) {
                     console.log("Duplicate User Exists!");
                     httpResponse.write("User Exists");
                     httpResponse.end();
                     duplicateUserFlag = false;
                     }
                     else {
                     console.log("Check 1");
                     duplicateUserFlag = true;
                     // Query for inserting user
                     sqlQuery = "INSERT INTO USERS (EMAIL, MOBILE, NRML_PWD, NAME) VALUES (?,?,?,?)";
                     dbConn.query(sqlQuery, [requestDataJson.email, requestDataJson.mobile, requestDataJson.nrml_pwd, requestDataJson.name],function (err, result) {
                                  if (err) {
                                  console.log("Error Occured!", err.message);
                                  httpResponse.write("Insert Fail");
                                  throw err;
                                  } // end if
                                  console.log("No of Users: ",result.affectedRows);
                                  rowsInserted = result.affectedRows;
                                  httpResponse.write("Insert Success");
                                  httpResponse.end();
                                  rawReqData = "";
                                  }); // end query()
                     // Get the user who is inserted - Fetch only the user id
                     var fetchQuery = "SELECT USER_ID FROM USERS WHERE MOBILE = ?";
                     var userMobile = requestDataJson.mobile;
                     dbConn.query(fetchQuery,[userMobile], function(err, result) {
                                  if (err) {
                                  httpResponse.write("Insert Fail");
                                  throw err;
                                  }
                                  console.log(this.sql);
                                  // Extract the user id from the result
                                  stringResultUserId = JSON.stringify(result);
                                  jsonUserId = JSON.parse(stringResultUserId)[0];
                                  userId = jsonUserId.USER_ID;
                                  // Create student id from the user id
                                  if (userId.toString().length == 3) { // Student ID column
                                  studentId = "ST000" + userId;
                                  }
                                  else if (userId.toString().length == 4) {
                                  studentId = "ST00" +userId;
                                  }
                                  else if (userId.toString().length == 5) {
                                  studentId = "ST0" +userId;
                                  }
                                  else if (userId.toString().length == 6) {
                                  studentId = "ST" +userId;
                                  }
                                  // Create date and other values
                                  registeredDate = today.getDate() + "-" + (today.getMonth() + 1) + "-" + today.getFullYear(); // Registered Date column
                                  console.log("User ID: ",registeredDate);
                                  // Update the user with the other columns such as student id, role and others
                                  var updateQuery = "UPDATE USERS SET STUDENT_ID = ?, PROFILE_CREATED = ?, ROLE = ?  WHERE USER_ID = ?";
                                  dbConn.query(updateQuery,[studentId,registeredDate,role,userId], function(err, result) {
                                               if (err) {
                                               httpResponse.write("Insert Fail");
                                               throw err;
                                               }
                                               console.log(this.sql);
                                               console.log("Values: ", studentId,registeredDate,role,active,userId);
                                               console.log("Rows Updated: ",result.affectedRows);
                                               });
                                  });
                     }
                     });
        rawReqData = "";
    } // end else
}
