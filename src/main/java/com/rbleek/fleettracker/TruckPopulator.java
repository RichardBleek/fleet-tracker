package com.rbleek.fleettracker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class TruckPopulator {

    private TruckRepository truckRepository;

    public TruckPopulator(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @PostConstruct
    public void Populate() {
        truckRepository.deleteAll().subscribe(null, null, () -> {
           Flux<Truck> trucks = trucks();
           truckRepository.insert(trucks).subscribe(truck -> log.info("Truck inserted: {}", truck));
        });
    }

    private Flux<Truck> trucks() {
        List<String> licensePlates = List.of("AN-WB-01", "AB-123-Z", "AA-BB-99");
        return Flux.fromStream(licensePlates.stream()).map(Truck::new);
    }
}
