package entity.skofnung.metadata;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Addresses extends Users {
    private String address;
    private String alias;
    private String createdBy;
    @GeneratedValue
    @Id
    private Integer id;
    private String note;
}
