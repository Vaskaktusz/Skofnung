package application.repository;

import application.entity.gungnir.Gungnir;
import application.entity.gungnir.metadata.Details;
import application.entity.gungnir.metadata.Device;
import application.entity.gungnir.metadata.System;
import application.entity.skofnung.database.Address;
import application.service.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

@Repository
public class GungnirRepository {
    @Autowired
    private RestTemplate restTemplate;

    public Gungnir findByAddress(Address address) {
        Gungnir gungnir = new Gungnir();
        gungnir.setDetails(restTemplate.exchange(address, Details.class, HttpMethod.GET, "/api/detail"));
        gungnir.setDevice(restTemplate.exchange(address, Device.class, HttpMethod.GET, "/api/device"));
        gungnir.setSystem(restTemplate.exchange(address, System.class, HttpMethod.GET, "/api/system"));
        return gungnir;
    }

    public void health(Address address) {
        restTemplate.exchange(address, Void.class, HttpMethod.GET, "/api/health");
    }
}