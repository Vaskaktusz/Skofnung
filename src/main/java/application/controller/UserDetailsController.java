package application.controller;

import application.entity.security.UserDetails;
import application.repository.UserDetailsRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class UserDetailsController {
    private final UserDetailsRepository userDetailsRepository;

    @PostMapping("/register")
    public UserDetails save(@RequestBody @Valid UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }
}