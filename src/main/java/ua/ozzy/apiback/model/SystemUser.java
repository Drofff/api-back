package ua.ozzy.apiback.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.ozzy.apiback.enums.Role;

import java.util.Collection;

import static java.util.Collections.singleton;

public abstract class SystemUser implements UserDetails {

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(this, getPassword());
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return singleton(getRole());
    }

    protected abstract Role getRole();

}
