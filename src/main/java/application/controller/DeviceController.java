package application.controller;

import application.entity.database.Address;
import application.entity.metadata.Device;
import application.entity.metadata.System;
import application.repository.DeviceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class DeviceController {
    private final DeviceRepository deviceRepository;

    @PostMapping("/device")
    public Device device(@RequestBody @Valid Address address) {
        return deviceRepository.device(address);
    }

    @PostMapping("/health")
    public void health(@RequestBody @Valid Address address) {
        deviceRepository.health(address);
    }

    @PostMapping("/system")
    public System system(@RequestBody @Valid Address address) {
        return deviceRepository.system(address);
    }
}