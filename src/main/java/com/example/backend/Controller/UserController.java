package com.example.backend.Controller;

import com.example.backend.Service.UserService;
import com.example.backend.pojo.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password){
        System.out.println(username + password);
        userService.login(username, password);
    }

    @PostMapping("/register")
    public void register(@RequestParam String username, @RequestParam String password){
        userService.register(username, password);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String username){
        userService.logout(username);
    }

    @PostMapping("/session")
    public boolean session(@RequestParam String username){
        return userService.session(username);
    }

    @GetMapping("/user")
    public UserEntity getUser(@RequestParam String username){
        System.out.println(username);
        return userService.getUser(username);
    }
}
