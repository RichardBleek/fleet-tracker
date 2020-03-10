package com.rbleek.fleettracker;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

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

    Mono<ServerResponse> insert(ServerRequest req) {
        Flux<Truck> carFlux = req.bodyToFlux(Truck.class)
                .flatMap(truckRepository::insert);
        return ok().body(carFlux, Truck.class);
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
