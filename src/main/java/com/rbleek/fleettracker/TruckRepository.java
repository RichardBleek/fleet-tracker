package com.rbleek.fleettracker;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TruckRepository extends ReactiveMongoRepository<Truck, String> {
}
