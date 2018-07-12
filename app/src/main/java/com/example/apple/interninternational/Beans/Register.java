package com.example.apple.interninternational.Beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Register implements Parcelable{
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    /**
     * Parcelable methods implemtation
     *
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * This method writes the objects into the destination parcel
     * @param dest: where to parcel the properties
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.mobile);
        dest.writeString(this.password);
        dest.writeString(this.verifyPassword);
    }

    protected Register(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        name = in.readString();
        email = in.readString();
        mobile = in.readString();
        password = in.readString();
        verifyPassword = in.readString();
    }

    public static final Creator<Register> CREATOR = new Creator<Register>() {
        @Override
        public Register createFromParcel(Parcel in) {
            return new Register(in);
        }

        @Override
        public Register[] newArray(int size) {
            return new Register[size];
        }
    };
}
