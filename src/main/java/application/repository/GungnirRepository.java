package application.repository;

import application.entity.gungnir.Gungnir;
import application.entity.gungnir.metadata.Details;
import application.entity.gungnir.metadata.Device;
import application.entity.gungnir.metadata.System;
import application.entity.skofnung.database.Address;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Optional;

@Repository
public class GungnirRepository {
    public Gungnir findByAddress(Address address) {
        Gungnir gungnir = new Gungnir();
        gungnir.setDetails(bodyToMono(address, Details.class, "/detail").orElse(new Details()));
        gungnir.setDevice(bodyToMono(address, Device.class, "/device").orElse(new Device()));
        gungnir.setSystem(bodyToMono(address, System.class, "/system").orElse(new System()));
        return gungnir;
    }

    private <K> Optional<K> bodyToMono(Address address, Class<K> clazz, String baseUrl) {
        WebClient webClient = WebClient.builder()
                .baseUrl(address.getLocation().concat(baseUrl))
                .defaultHeader("Authentication", Base64.getEncoder().encodeToString(String.join(":", address.getUsername(), address.getPassword()).getBytes(StandardCharsets.UTF_8)))
                .build();
        return webClient.get().exchangeToMono(clientResponse -> clientResponse.bodyToMono(clazz)).blockOptional(Duration.ofMillis(30_000L));
    }
}
