package com.objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetails {
    String id;
    String firstName;
    String lastName;
    String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static EmployeeDetails jsonFormat(JSONObject json){
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.id = String.valueOf(json.get("id"));
        employeeDetails.firstName = String.valueOf(json.get("firstName"));
        employeeDetails.lastName = String.valueOf(json.get("lastName"));
        employeeDetails.photo = String.valueOf(json.get("photo"));
        return employeeDetails;
    }
}
