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
public class Login {
    private String name;
    private String email;
    private String password;

    public Login(String name, String email, String nrml_pwd) {
        this.name = name;
        this.email = email;
        this.password = nrml_pwd;
    }

    public Login() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }
}
