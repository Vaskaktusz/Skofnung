package application.configuration;

import application.repository.UserDetailsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, UserDetailsRepository userDetailsRepository) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(userDetailsRepository::findByUsername)
                .httpBasic()
                .and()
                .build();
    }
}