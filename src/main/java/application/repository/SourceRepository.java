package application.repository;

import application.entity.skofnung.database.Source;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SourceRepository extends CrudRepository<Source, Long> {
}