package application.entity.skofnung.database;

@lombok.Data
@javax.persistence.Entity
public abstract class Id {
    @javax.persistence.Id
    @javax.persistence.GeneratedValue
    private long id;
}
