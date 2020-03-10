package com.rbleek.fleettracker;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Truck {

    public Truck() {
    }

    public Truck(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Id
    String id;
    String licensePlate;
}
