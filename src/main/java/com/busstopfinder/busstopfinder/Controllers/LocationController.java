package com.busstopfinder.busstopfinder.Controllers;

import com.busstopfinder.busstopfinder.model.Location;
import com.busstopfinder.busstopfinder.model.LocationType;
import com.busstopfinder.busstopfinder.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLocation(@RequestBody Location location) {
        try {
            Location saved = locationService.saveLocation(location);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping(value = "/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLocationsByType(@PathVariable LocationType type) {
        List<Location> locations = locationService.getLocationsByType(type);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping(value = "/children/{parentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getChildrenByParentId(@PathVariable Long parentId) {
        List<Location> locations = locationService.getChildrenByParentId(parentId);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLocationById(@PathVariable Long id) {
        try {
            Location location = locationService.getLocationById(id);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}