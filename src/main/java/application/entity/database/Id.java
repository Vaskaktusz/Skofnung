package application.entity.database;

@lombok.Data
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "SOURCES")
public abstract class Id {
    @jakarta.persistence.GeneratedValue
    @jakarta.persistence.Id
    private long id;
}