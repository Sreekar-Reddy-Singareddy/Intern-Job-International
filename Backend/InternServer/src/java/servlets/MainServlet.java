/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.services.LoginService;
import model.services.NationalInternshipService;
import model.services.RegisterService;
import model.validations.LoginValidation;

/**
 *
 * @author apple
 */
public class MainServlet extends HttpServlet {

    private LoginService loginService = new LoginService();
    private RegisterService registerService = new RegisterService();
    private NationalInternshipService nationalInternshipService = new NationalInternshipService();

    /**
     * Every request will have a flag which tells the servlet what to do next
     * This string is that flag
     */
    private String requestFlag = null;
    
    /**
     * Every request will get some response back
     * The response is in the string format only.
     * This member contains that string.
     */
    private String responseString = null;
    
    /**
     * Every response will have one writer, 
     * through which data can be written to the response.
     */
    private PrintWriter responseWriter = null;
    
    /**
     * Universal GSON object used to work across with JSON and java beans.
     */
    private Gson gson = new Gson();

    // Http request is of GET method
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        responseWriter = resp.getWriter();
        System.out.println("Inside GET");
        doPost(req, resp);
    }

    // Http request is of POST method
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        responseWriter = resp.getWriter();
        System.out.println("Request Method: " + req.getMethod());
        requestFlag = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1).toLowerCase();
        System.out.println("Request Flag: " + requestFlag);
        try {
            // Proceed with the request data
            beginServerWork(req, resp);
            responseWriter.write(responseString);
            responseWriter.close();
            System.out.println("Response String: "+responseString);
        } catch (SQLException ex) {
            System.out.println("Some SQL exception -> "+ex.getMessage());
        }
    }
    
    /**
     * This method is the starting point of the
     * whole back-end processing.
     * It uses various methods for various purposes like
     * converting data into JSON format etc.
     * @param req
     * @param resp
     * @throws IOException 
     */
    private void beginServerWork (HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String reqDataInJsonFormat = extractRequestData(req);
        
        // Based on the request flag, apt service class is called
        if (requestFlag.equals("userlogin")) {
            responseString = loginService.serveRequestWithData(reqDataInJsonFormat,gson);
        }
        else if (requestFlag.equals("userregister")) {
            responseString = registerService.serveRequestWithData(reqDataInJsonFormat, gson);
            System.out.println("Response from register: "+responseString);
        }
        else if (requestFlag.equals("branches")) {
            responseString = nationalInternshipService.getAllBranches(gson);
        }
    }

    /**
     * This method takes the request and decodes the data in it into JSON format
     * string
     * @param req
     * @return
     */
    private String extractRequestData(HttpServletRequest req) throws IOException {
        // Convert the input stream of data into string
        InputStream inputStream = req.getInputStream();
        byte[] data = new byte[128];
        int bytesRead = 0;
        StringBuilder jsonStringBuilder = new StringBuilder("");
        while ((bytesRead = inputStream.read(data)) != -1) {
            jsonStringBuilder.append(new String(data, 0, bytesRead));
        }
        return jsonStringBuilder.toString();
    }

}
