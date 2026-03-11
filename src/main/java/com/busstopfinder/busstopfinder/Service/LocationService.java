package com.busstopfinder.busstopfinder.Service;

import com.busstopfinder.busstopfinder.model.Location;
import com.busstopfinder.busstopfinder.model.LocationType;
import com.busstopfinder.busstopfinder.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location saveLocation(Location location) {
        if (locationRepository.existsByNameAndType(
                location.getName(), location.getType())) {
            throw new RuntimeException(
                location.getType() + " with this name already exists!");
        }

        if (location.getParent() != null) {
            Location parent = locationRepository.findById(location.getParent().getId())
                    .orElseThrow(() -> new RuntimeException("Parent location not found!"));
            location.setParent(parent);
        }

        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public List<Location> getLocationsByType(LocationType type) {
        return locationRepository.findByType(type);
    }

    public List<Location> getChildrenByParentId(Long parentId) {
        return locationRepository.findByParentId(parentId);
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found!"));
    }
}