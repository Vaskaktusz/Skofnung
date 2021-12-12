package application.repository;

import application.entity.gungnir.Gungnir;
import application.entity.gungnir.metadata.Details;
import application.entity.gungnir.metadata.Device;
import application.entity.gungnir.metadata.System;
import application.entity.skofnung.database.Address;
import application.facade.GungnirFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GungnirRepository {
    private final GungnirFacade gungnirFacade;

    public Gungnir findByAddress(Address address) {
        Gungnir gungnir = new Gungnir();
        gungnir.setDetails(gungnirFacade.bodyToMono(address, Details.class, "/api/detail"));
        gungnir.setDevice(gungnirFacade.bodyToMono(address, Device.class, "/api/device"));
        gungnir.setSystem(gungnirFacade.bodyToMono(address, System.class, "/api/system"));
        return gungnir;
    }

    public void health(Address address) {
        gungnirFacade.bodyToMono(address, Void.class, "/api/health");
    }
}
