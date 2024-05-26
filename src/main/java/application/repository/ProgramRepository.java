package application.repository;

import application.entity.database.Program;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProgramRepository extends CrudRepository<Program, Long> {
}