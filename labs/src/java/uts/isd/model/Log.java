/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 * @author super
 */
public class Log {

    private long logID;

    private int userID;
    private Timestamp logTime;
    private String activityType;
    private String activityDetails;
    private long relatedID;

    public Log(long logID, int userID, Timestamp logTime, String activityType, String activityDetails) {
        this.logID = logID;
        this.userID = userID;
        this.logTime = logTime;
        this.activityType = activityType;
        this.activityDetails = activityDetails;
    }
    
    public long geLogID(){
        return logID;
    }
    
    public int getUserID() {
        return userID;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getActivityDetails() {
        return activityDetails;
    }
    
    public void setRelatedID(long relatedID) {
        this.relatedID = relatedID;
    }
    
    public long getRelatedID() {
        return relatedID;
    }
    
}
