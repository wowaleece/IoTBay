/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

/**
 *
 * @author super
 */
public class Address {
    private int addressID;
    private String streetName;
    private String unitNumber;
    private String suburb;
    private int postcode;
    private String state;
    private String country; 

    public Address(int addressID, String streetName, String unitNumber, String suburb, int postcode, String state, String country) {
        this.addressID = addressID;
        this.streetName = streetName;
        this.unitNumber = unitNumber;
        this.suburb = suburb;
        this.postcode = postcode;
        this.state = state;
        this.country = country;
    }

    
    public int getAddressID() {
        return addressID;
    }
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
