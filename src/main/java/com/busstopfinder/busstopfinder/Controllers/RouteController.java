package com.busstopfinder.busstopfinder.Controllers;

import com.busstopfinder.busstopfinder.model.Route;
import com.busstopfinder.busstopfinder.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRoute(@RequestBody Route route) {
        try {
            Route saved = routeService.saveRoute(route);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllRoutes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "routeNumber") String sortBy) {
        Page<Route> routes = routeService.getAllRoutes(page, size, sortBy);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }
    @PostMapping(value = "/{routeId}/busstops/{busStopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBusStopToRoute(
        @PathVariable Long routeId,
        @PathVariable Long busStopId) {
    try {
        Route updated = routeService.addBusStopToRoute(routeId, busStopId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    } catch (RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
}