package application.repository;

import application.entity.skofnung.database.User;
import org.springframework.data.repository.CrudRepository;

/**
 * TODO: Database tables are wrongly named and authentication is not working.
 * In addition, User model cannot be used by both CRUD repositories because it should have a unique flag in one case.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}