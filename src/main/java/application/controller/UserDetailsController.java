package application.controller;

import application.entity.security.UserDetails;
import application.repository.UserDetailsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class UserDetailsController {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @PostMapping("/register")
    public UserDetails save(@RequestBody @Valid UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }
}