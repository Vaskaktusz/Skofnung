package application.repository;

import application.entity.gungnir.Gungnir;
import application.entity.gungnir.metadata.Details;
import application.entity.gungnir.metadata.Device;
import application.entity.gungnir.metadata.System;
import application.entity.skofnung.database.Address;
import application.facade.GungnirFacade;
import org.springframework.stereotype.Repository;

@Repository
public class GungnirRepository extends GungnirFacade {
    public Gungnir findByAddress(Address address) {
        Gungnir gungnir = new Gungnir();
        gungnir.setDetails(bodyToMono(address, Details.class, "/api/detail"));
        gungnir.setDevice(bodyToMono(address, Device.class, "/api/device"));
        gungnir.setSystem(bodyToMono(address, System.class, "/api/system"));
        return gungnir;
    }

    public void health(Address address) {
        bodyToMono(address, Void.class, "/api/health");
    }
}
