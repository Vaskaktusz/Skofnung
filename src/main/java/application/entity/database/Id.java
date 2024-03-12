package application.entity.database;

@lombok.Data
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "SKOFNUNG")
public abstract class Id {
    @jakarta.persistence.GeneratedValue
    @jakarta.persistence.Id
    private long id;
}