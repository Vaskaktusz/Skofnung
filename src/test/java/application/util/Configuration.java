package application.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@org.springframework.context.annotation.Configuration
@Data
public class Configuration {
    @Value("${location}")
    private String location;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
}