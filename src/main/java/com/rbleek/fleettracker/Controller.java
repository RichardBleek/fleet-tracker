package com.rbleek.fleettracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/truck")
public class Controller {

    @GetMapping
    public Flux<Truck> getTruck() {
        return Flux.just(new Truck("aa-aa-11"));
    }
}
