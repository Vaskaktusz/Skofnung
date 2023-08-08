package application.repository;

import application.entity.skofnung.security.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<UserDetails, String> {
    UserDetails findByUsername(String username);
}