package com.example.apple.interninternational.Beans;

public class Login {
    private String name;
    private String email;
    private String password;

    public Login(String name, String email, String nrml_pwd) {
        this.name = name;
        this.email = email;
        this.password = nrml_pwd;
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

    public String getNrml_pwd() {
        return password;
    }

    public void setNrml_pwd(String nrml_pwd) {
        this.password = nrml_pwd;
    }
}
