package application.repository;

import application.entity.gungnir.Gungnir;
import application.entity.gungnir.metadata.Details;
import application.entity.gungnir.metadata.Device;
import application.entity.gungnir.metadata.System;
import application.entity.skofnung.database.Address;
import application.service.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GungnirRepository {
    @Autowired
    private RestTemplate restTemplate;

    public Gungnir findByAddress(Address address) {
        Gungnir gungnir = new Gungnir();
        gungnir.setDetails(restTemplate.httpGet(address, "/api/detail", Details.class));
        gungnir.setDevice(restTemplate.httpGet(address, "/api/device", Device.class));
        gungnir.setSystem(restTemplate.httpGet(address, "/api/system", System.class));
        return gungnir;
    }

    public void health(Address address) {
        restTemplate.httpGet(address, "/api/health", Void.class);
    }
}