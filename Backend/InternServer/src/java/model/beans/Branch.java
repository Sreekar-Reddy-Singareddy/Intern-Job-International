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
public class Branch {
    
    private String name;
    private int noOfOpportunities;
    private int id;
    private boolean isOpen;

    public Branch() {
    }

    public Branch(String name, int noOfOpportunities, int id) {
        this.name = name;
        this.noOfOpportunities = noOfOpportunities;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfOpportunities() {
        return noOfOpportunities;
    }

    public void setNoOfOpportunities(int noOfOpportunities) {
        this.noOfOpportunities = noOfOpportunities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    
    
    
}
