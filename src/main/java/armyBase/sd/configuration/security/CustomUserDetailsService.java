package armyBase.sd.configuration.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import armyBase.sd.dao.UserDAO;
import armyBase.sd.model.UserDetailed;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailed optionalUser = usersRepository.findByEmail(username);
        
        
        if(optionalUser == null) 
        	throw new UsernameNotFoundException("Username not found");
        return new CustomUserDetails(optionalUser);
    }
}