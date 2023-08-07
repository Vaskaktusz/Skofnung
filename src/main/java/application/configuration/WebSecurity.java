package application.configuration;

import application.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return userRepository::findByUsername;
    }
}