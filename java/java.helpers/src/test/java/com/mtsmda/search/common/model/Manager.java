package com.mtsmda.search.common.model;

/**
 * Created by dminzat on 9/20/2016.
 */
public class Manager extends Person{

    private String managerName;

    public Manager(String managerName) {
        this.managerName = managerName;
    }

    public Manager(String firstName, String lastName, String managerName) {
        super(firstName, lastName);
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}