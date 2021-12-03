package application.controller;

import application.entity.gungnir.Gungnir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import application.repository.AddressRepository;
import application.repository.GungnirRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GungnirController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GungnirRepository gungnirRepository;

    @GetMapping("/gungnir/{location}")
    public List<Gungnir> getGungnir(@PathVariable String location) {
        return addressRepository.findByLocation(location)
                .parallelStream()
                .map(gungnirRepository::findByAddress)
                .collect(Collectors.toList());
    }
}
