package com.javajo.javajo_jewels.controller;

import com.javajo.javajo_jewels.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    @PostMapping("/register")
    public User createUser (@RequestBody User request){
        System.out.println("called createUser");
        request.setUserId(1);
        return request;
    }

    @PostMapping("/login")
    public User login(@RequestBody User request) {
        System.out.println("called createUser");
        return request;
    };

    @GetMapping("/profile")
    public User getProfile(@RequestHeader(name = "Authorization") String authToken) {
        System.out.println("called createUser");
        System.out.println("authToken=" + authToken);
        User user = new User();
        user.setUserId(999);
        user.setUserName("test");
        return user;
    }


}
