/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

/**
 *
 * @author apple
 */
public class Register{
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String mobile = "";
    private String password = "";
    private String verifyPassword = "";
    private String name = "";

    public Register() {

    }

    public Register(String firstName, String lastName, String email, String mobile, String password, String verifyPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.verifyPassword = verifyPassword;
        this.name = this.firstName.trim().toLowerCase().substring(0,1).toUpperCase() +
                    " " +
                    this.lastName.trim().toLowerCase().substring(0,1).toUpperCase();
    }

    public void formatName() {
        if (this.firstName.isEmpty() || this.lastName.isEmpty()){
            return;
        }
        this.name = this.firstName.substring(0,1).toUpperCase() + this.firstName.substring(1) +
                " " +
                this.lastName.substring(0,1).toUpperCase() + this.lastName.substring(1);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim().toLowerCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim().toLowerCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
