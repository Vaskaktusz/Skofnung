package application.repository;

import application.entity.security.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<UserDetails, String> {
    UserDetails findByUsername(String username);
}