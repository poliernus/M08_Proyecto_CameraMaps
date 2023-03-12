package com.dam.projectem08_uf2_yayserziyadi_polmelara;

import android.graphics.Bitmap;

public class photo {
    private Bitmap photo;
    private String latitude;
    private String longtitude;

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
}

