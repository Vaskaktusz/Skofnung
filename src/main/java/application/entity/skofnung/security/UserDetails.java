package application.entity.skofnung.security;

import application.converter.DatabaseEncryptor;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Entity
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    @Convert(converter = DatabaseEncryptor.class)
    @Id
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