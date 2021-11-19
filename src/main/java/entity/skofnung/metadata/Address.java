package entity.skofnung.metadata;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Address {
    private String address;
    private String alias;
    private String createdBy;
    @GeneratedValue
    @Id
    private Integer id;
    private String note;
}
