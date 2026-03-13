package com.busstopfinder.busstopfinder.Service;

import com.busstopfinder.busstopfinder.model.BusStop;
import com.busstopfinder.busstopfinder.model.Route;
import com.busstopfinder.busstopfinder.repositories.BusStopRepository;
import com.busstopfinder.busstopfinder.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route saveRoute(Route route) {
        if (routeRepository.existsByRouteNumber(route.getRouteNumber())) {
            throw new RuntimeException("Route with this number already exists!");
        }
        return routeRepository.save(route);
    }

    public Page<Route> getAllRoutes(int page, int size,String sortBy) {
        return routeRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }
      @Autowired
    private BusStopRepository busStopRepository;

    public Route addBusStopToRoute(Long routeId, Long busStopId) {
         Route route = routeRepository.findById(routeId)
            .orElseThrow(() -> new RuntimeException("Route not found!"));
    
    BusStop busStop = busStopRepository.findById(busStopId)
            .orElseThrow(() -> new RuntimeException("Bus stop not found!"));
    
    route.getBusStops().add(busStop);
    return routeRepository.save(route);
   
    


}
}

