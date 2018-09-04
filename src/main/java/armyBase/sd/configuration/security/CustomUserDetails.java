package armyBase.sd.configuration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import armyBase.sd.model.UserDetailed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SuppressWarnings("serial")
public class CustomUserDetails extends UserDetailed implements UserDetails {

    public CustomUserDetails(final UserDetailed user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

    	List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
    	roles.add(new SimpleGrantedAuthority("ROLE_" + getRol()));
       
        return roles;
    }

    @Override
    public String getPassword() {
    	System.out.println("PAss " + super.getPass());
        return super.getPass();
    }

    @Override
    public String getUsername() {
    	System.out.println("email "+super.getEmail());
        return super.getEmail();
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