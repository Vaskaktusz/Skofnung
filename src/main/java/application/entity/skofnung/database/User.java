package application.entity.skofnung.database;

import application.converter.DatabaseEncryptor;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends Id implements UserDetails {
    @Convert(converter = DatabaseEncryptor.class)
    @NotBlank
    private String username;
    @Convert(converter = DatabaseEncryptor.class)
    @NotBlank
    private String password;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}