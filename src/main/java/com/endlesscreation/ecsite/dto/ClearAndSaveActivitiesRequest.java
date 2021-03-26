package com.endlesscreation.ecsite.dto;

import com.endlesscreation.ecsite.domain.Location;

public class ClearAndSaveActivitiesRequest {

    private String imageUrl;

    private Location location;

    private ClearAndSaveActivitiesRequest() {
    }

    public ClearAndSaveActivitiesRequest(String imageUrl, Location location) {
        this.imageUrl = imageUrl;
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Location getLocation() { return location; }
}
