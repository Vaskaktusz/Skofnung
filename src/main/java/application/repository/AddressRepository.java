package application.repository;

import application.entity.skofnung.database.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
    List<Address> findByLocation(String location);
}
