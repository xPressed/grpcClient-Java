package ru.xpressed.grpcclientjava.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
@NoArgsConstructor
public class User implements UserDetails {
    public User(String username, String password, String repeated) {
        this.username = username;
        this.password = password;
        this.repeated = repeated;
    }

    @NotEmpty(message = "Username can not be empty!")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,15}$", message = "Only numbers and at least 3 and not more than 15 letters!")
    private String username;

    @NotEmpty(message = "Password can not be empty!")
    private String password;

    @NotEmpty(message = "Repeated password can not be empty!")
    private String repeated;

    private String old;

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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
