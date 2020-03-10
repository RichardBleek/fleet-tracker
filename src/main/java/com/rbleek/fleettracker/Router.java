package com.rbleek.fleettracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> route(TruckService truckService) {
        return RouterFunctions
                .route(GET("/trucks"),
                        serverRequest -> ServerResponse.ok().body(truckService.findAll(), Truck.class))
                .andRoute(POST("/truck"), truckService::insert)
                .andRoute(GET("/trucks/locations"),
                        serverRequest -> ServerResponse.ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(truckService.locationUpdates(), LocationEvent.class))
                ;
    }
}
