package com.busstopfinder.busstopfinder.Service;

import com.busstopfinder.busstopfinder.model.User;
import com.busstopfinder.busstopfinder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists!");
        }
        User saved = userRepository.save(user);
             return userRepository.findById(saved.getId())
            .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public List<User> getUsersByProvinceCode(String code) {
        return userRepository.findByProvince_Code(code);
    }

    public List<User> getUsersByProvinceName(String name) {
        return userRepository.findByProvince_Name(name);
    }
}
