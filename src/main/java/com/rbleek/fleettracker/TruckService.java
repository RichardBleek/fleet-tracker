package com.rbleek.fleettracker;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@Service
public class TruckService {

    private TruckRepository truckRepository;
    private Random random = new Random();

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    Flux<Truck> findAll(){
        return truckRepository.findAll();
    }

    Flux<LocationEvent> locationUpdates() {
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> generateLocationUpdate());
    }

    private Flux<LocationEvent> generateLocationUpdate() {
        return truckRepository.findAll().map(this::randomLocationEventForTruck);
    }

    private LocationEvent randomLocationEventForTruck(Truck truck) {
        return new LocationEvent(truck, random.nextDouble(), random.nextDouble());
    }
}
