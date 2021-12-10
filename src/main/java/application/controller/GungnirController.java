package application.controller;

import application.entity.gungnir.Gungnir;
import application.entity.skofnung.database.Address;
import application.repository.GungnirRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class GungnirController {

    private final GungnirRepository gungnirRepository;

    @PostMapping("/gungnirs/search/findByAddress")
    public Gungnir findByAddress(@RequestBody @Valid Address address) {
        return gungnirRepository.findByAddress(address);
    }
}
