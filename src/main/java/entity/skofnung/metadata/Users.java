package entity.skofnung.metadata;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Users {
    private Boolean enabled;
    @Id
    private String username;
    private String password;
}
