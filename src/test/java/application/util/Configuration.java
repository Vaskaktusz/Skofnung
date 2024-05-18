package application.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@org.springframework.context.annotation.Configuration
@ConfigurationProperties("digitalocean")
@Data
public class Configuration {
    private String location;
    private String username;
    private String password;
}