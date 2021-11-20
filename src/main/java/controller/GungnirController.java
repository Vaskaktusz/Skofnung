package controller;

import entity.gungnir.Gungnir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import repository.AddressRepository;
import repository.GungnirRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GungnirController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GungnirRepository gungnirRepository;

    @GetMapping("/gungnir/{username}")
    public List<Gungnir> getGungnir(@PathVariable String username) {
        return addressRepository.findByUsername(username)
                .parallelStream()
                .map(gungnirRepository::findByAddress)
                .collect(Collectors.toList());
    }
}
