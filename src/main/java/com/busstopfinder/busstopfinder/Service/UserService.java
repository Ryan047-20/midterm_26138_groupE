package com.busstopfinder.busstopfinder.Service;

import com.busstopfinder.busstopfinder.model.User;
import com.busstopfinder.busstopfinder.model.Location;
import com.busstopfinder.busstopfinder.repositories.UserRepository;
import com.busstopfinder.busstopfinder.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }

        Location village = locationRepository.findById(user.getVillage().getId())
                .orElseThrow(() -> new RuntimeException("Village not found!"));
        user.setVillage(village);

        return userRepository.save(user);
    }

    public List<User> getUsersByProvinceId(Long provinceId) {
        return userRepository.findByProvinceId(provinceId);
    }

    public List<User> getUsersByProvinceName(String provinceName) {
        return userRepository.findByProvinceName(provinceName);
    }
}
