package application.controller;

import application.entity.gungnir.Gungnir;
import application.entity.skofnung.database.Address;
import application.repository.GungnirRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public final record GungnirController(GungnirRepository gungnirRepository) {
    @PostMapping("/gungnirs/search/findByAddress")
    public Gungnir findByAddress(@RequestBody @Valid Address address) {
        return gungnirRepository.findByAddress(address);
    }
}