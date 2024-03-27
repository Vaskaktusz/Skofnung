package application.repository;

import application.entity.database.Address;
import application.entity.metadata.Device;
import application.entity.metadata.System;
import application.service.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceRepository {
    @Autowired
    private RestTemplate restTemplate;

    public Device device(Address address) {
        return restTemplate.httpGet(address, "/api/device", Device.class);
    }

    public void health(Address address) {
        restTemplate.httpGet(address, "/api/health", Void.class);
    }

    public System system(Address address) {
        return restTemplate.httpGet(address, "/api/system", System.class);
    }
}