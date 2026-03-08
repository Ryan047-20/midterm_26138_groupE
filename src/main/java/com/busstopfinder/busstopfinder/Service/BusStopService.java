package com.busstopfinder.busstopfinder.Service;

import com.busstopfinder.busstopfinder.model.BusStop;
import com.busstopfinder.busstopfinder.repositories.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BusStopService {

    @Autowired
    private BusStopRepository busStopRepository;

    public BusStop saveBusStop(BusStop busStop) {
        if (busStopRepository.existsByName(busStop.getName())) {
            throw new RuntimeException("Bus stop with this name already exists!");
        }
        BusStop saved = busStopRepository.save(busStop);
            return busStopRepository.findById(saved.getId())
            .orElseThrow(() -> new RuntimeException("Bus stop not found!"));
    }

    public List<BusStop> getAllBusStops() {
        return busStopRepository.findAll();
    }

    public BusStop getBusStopById(Long id) {
        return busStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus stop not found!"));
    }

}
