package application.repository;

import application.entity.skofnung.database.Address;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AddressRepository extends Repository<Address, String> {
    List<Address> findByLocation(String location);
}
