package com.busstopfinder.busstopfinder.Controllers;

import com.busstopfinder.busstopfinder.model.User;
import com.busstopfinder.busstopfinder.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            User saved = userService.saveUser(user);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/province/{provinceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersByProvinceId(@PathVariable Long provinceId) {
        List<User> users = userService.getUsersByProvinceId(provinceId);
        if (users.isEmpty()) {
            return new ResponseEntity<>("No users found in this province.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/province/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersByProvinceName(@PathVariable String name) {
        List<User> users = userService.getUsersByProvinceName(name);
        if (users.isEmpty()) {
            return new ResponseEntity<>("No users found in this province.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}