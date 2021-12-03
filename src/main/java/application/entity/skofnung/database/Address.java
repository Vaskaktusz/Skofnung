package application.entity.skofnung.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Address {
    @Id
    private String location;
    private String username;
    private String password;
}
