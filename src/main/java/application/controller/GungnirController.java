package application.controller;

import application.entity.gungnir.Gungnir;
import application.entity.skofnung.database.Address;
import application.repository.GungnirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class GungnirController {
    @Autowired
    private GungnirRepository gungnirRepository;

    @PostMapping("/gungnirs/search/findByAddress")
    public Gungnir findByAddress(@RequestBody @Valid Address address) {
        return gungnirRepository.findByAddress(address);
    }

    @PostMapping("/health")
    public void health(@RequestBody @Valid Address address) {
        gungnirRepository.health(address);
    }
}
