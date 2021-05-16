/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author super
 */
public class Address implements Serializable {
    private int addressID;
    private String streetName;
    private String unitNumber;
    private String suburb;
    private int postcode = 0;
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
    
    public Address(ResultSet rs) throws SQLException{
        this.addressID = rs.getInt("addressID");
        this.unitNumber = rs.getString("unitNumber");
        this.streetName = rs.getString("streetName");
        this.suburb = rs.getString("suburb");
        this.postcode = rs.getInt("postcode");
        this.state = rs.getString("state");
        this.country = rs.getString("country");
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
    

   
    @Override
    public String toString(){
        /*
        StringBuilder str = new StringBuilder();
        Field[] fields = Address.class.getDeclaredFields();
		//for (int i = 0; i < fields.length; i++) {
                for(Field field:fields){
			str.append(field.get(this)).append(", ");
		} */
        //return str.toString();
        StringBuilder str = new StringBuilder();
        if(streetName != null) str.append(streetName);
        if(unitNumber != null) str.append(", " + unitNumber);
        if(suburb != null) str.append(", " + suburb);
        if(postcode != 0) str.append(", " + postcode);
        if(state != null) str.append(", " + state);
        if(country != null) str.append(", " + country);
        return str.toString();
    }
    
}
