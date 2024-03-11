package application.controller;

import application.entity.gungnir.Gungnir;
import application.entity.skofnung.database.Address;
import application.repository.DeviceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @PostMapping("/health")
    public void health(@RequestBody @Valid Address address) {
        deviceRepository.health(address);
    }

    @PostMapping("/search")
    public Gungnir search(@RequestBody @Valid Address address) {
        return deviceRepository.search(address);
    }
}