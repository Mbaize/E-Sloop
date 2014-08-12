package com.lelander.mbaize.e_sloop;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Micah on 8/7/2014.
 */
public class PositionAvailable {


    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getBoatImage() {
        return boatImage;
    }

    public void setBoatImage(String boatImage) {
        this.boatImage = boatImage;
    }

    public String getBoatingActivity() {
        return boatingActivity;
    }

    public void setBoatingActivity(String boatingActivity) {
        this.boatingActivity = boatingActivity;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public String getExperiencePref() {
        return experiencePref;
    }

    public void setExperiencePref(String experiencePref) {
        this.experiencePref = experiencePref;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String positions;
    private String boatImage;
    private String boatingActivity;
    private String departure;
    private String destination;
    private String boatType;
    private String experiencePref;
    private String email;


}
