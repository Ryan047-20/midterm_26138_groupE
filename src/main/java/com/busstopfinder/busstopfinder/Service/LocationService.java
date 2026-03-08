package com.busstopfinder.busstopfinder.Service;

import com.busstopfinder.busstopfinder.model.Location;
import com.busstopfinder.busstopfinder.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location saveLocation(Location location) {
        if (locationRepository.existsByStreetAndSector(location.getStreet(), location.getSector())) {
            throw new RuntimeException("Location with this street and sector already exists!");
        }
        Location saved = locationRepository.save(location);
         return locationRepository.findById(saved.getId())
            .orElseThrow(() -> new RuntimeException("Location not found!"));
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found!"));
    }
}
