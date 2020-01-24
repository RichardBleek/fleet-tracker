package com.rbleek.fleettracker;

import lombok.Getter;

@Getter
public class LocationEvent {

    Truck truck;
    Double longitude;
    Double latitude;

    public LocationEvent(Truck truck, Double longitude, Double latitude) {
        this.truck = truck;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
