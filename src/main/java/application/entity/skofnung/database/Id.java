package application.entity.skofnung.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Data
@Entity
public abstract class Id {
    @GeneratedValue
    @jakarta.persistence.Id
    private long id;
}