package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.AuthResponseDTO;
import com.example.DTO.LoginDTO;
import com.example.Entity.User;
import com.example.Security.JwtUtil;
import com.example.Service.UserService;

@RestController
@RequestMapping("/UserCtrl")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/test")
    public String test() {
        return "working.....";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return userService.register(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {

        return userService.getById(id);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginDTO dto) {

        User user = userService.login(dto.getEmail(),dto.getPassword() );

        if (user != null) {

            String token =jwtUtil.generateToken(user.getEmail() );

            return new AuthResponseDTO( token, "Login Successful" );
        }

        return new AuthResponseDTO( null,  "Invalid Login");
    }
}