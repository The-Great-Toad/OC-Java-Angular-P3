package oc.rental.rental_oc.controller;

import oc.rental.rental_oc.model.RegisterRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
@CrossOrigin(origins = "*")
public class AuthController {

    @PostMapping("register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return "Registration successful; %nname: %s, %nemail:%s, %npassword: %s"
                .formatted(registerRequest.name(), registerRequest.email(), registerRequest.password());
    }
}
