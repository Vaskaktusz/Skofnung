package application.controller;

import application.entity.gungnir.Gungnir;
import application.entity.skofnung.database.Address;
import application.repository.GungnirRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GungnirController {

    private final GungnirRepository gungnirRepository;

    @GetMapping("/gungnir")
    public Gungnir getGungnir(@RequestParam String location, @RequestParam String username, @RequestParam String password) {
        Address address = new Address();
        address.setLocation(location);
        address.setUsername(username);
        address.setPassword(password);
        return gungnirRepository.findByAddress(address);
    }
}
