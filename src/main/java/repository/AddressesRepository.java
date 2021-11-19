package repository;

import entity.skofnung.metadata.Addresses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressesRepository extends CrudRepository<Addresses, Long> {
    List<Addresses> findByUsername(String username);
}
