package com.lelander.mbaize.e_sloop;

import android.media.Image;

/**
 * Created by Micah on 8/1/2014.
 */
public class PositionWanted {


    private String boatingActivity;
    private String profileImage;
    private String positions;
    private String boatType;
    private String experienceHad;
    private String email;

    public String getBoatingActivity() {
        return boatingActivity;
    }

    public void setBoatingActivity(String boatingActivity) {
        this.boatingActivity = boatingActivity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getProfileImage() {
        return profileImage;
    }

    public String getPositions() {
        return positions;
    }


    public String getBoatType() {
        return boatType;
    }

    public String getExperienceHad() {
        return experienceHad;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public void setExperienceHad(String experienceHad) {
        this.experienceHad = experienceHad;
    }
}
