package entity.skofnung.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public abstract class Group {
    private String createdBy;
    @GeneratedValue
    @Id
    private Long id;
    private String note;
}
