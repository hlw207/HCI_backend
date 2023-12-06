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
    public String login(@RequestParam String info, @RequestParam String password){
        return userService.login(info, password);
    }

    @PostMapping("/loginPhone")
    public void loginPhone(@RequestParam String phone){
        userService.login(phone);
    }

    @PostMapping("/register")
    public void register(@RequestParam String phone, @RequestParam String username, @RequestParam String password){
        userService.register(phone, username, password);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String phone){
        userService.logout(phone);
    }

    @PostMapping("/session")
    public boolean session(@RequestParam String phone){
        return userService.session(phone);
    }

    @GetMapping("/user")
    public UserEntity getUser(@RequestParam String phone){
        return userService.getUser(phone);
    }

    @PostMapping("/user/profile")
    public void updateProfile(@RequestParam String phone, @RequestParam String path){
        userService.updateProfile(phone, path);
    }

    @PostMapping("/user/info")
    public void updateInfo(@RequestParam String phone, @RequestParam String username, @RequestParam String autoGraph){
        userService.updateInfo(phone, username, autoGraph);
    }

    @GetMapping("/user/password")
    public void passwordCheck(@RequestParam String phone, @RequestParam String password){
        userService.passwordCheck(phone, password);
    }

    @PostMapping("/user/password")
    public void updatePassword(@RequestParam String phone, @RequestParam String password){
        userService.updatePassword(phone, password);
    }

    @PostMapping("/user/phone")
    public void updatePhone(@RequestParam String phone, @RequestParam String newPhone){
        userService.updatePhone(phone, newPhone);
    }

    @GetMapping("/user/use")
    public boolean checkName(@RequestParam String username){
        return userService.checkName(username);
    }

    @GetMapping("/user/ifPassword")
    public boolean hasPassword(@RequestParam String phone){
        return userService.hasPassword(phone);
    }

}
