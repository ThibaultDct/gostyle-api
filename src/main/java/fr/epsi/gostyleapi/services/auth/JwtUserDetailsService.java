package fr.epsi.gostyleapi.services.auth;

import fr.epsi.gostyleapi.external.entities.UserEntity;
import fr.epsi.gostyleapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity query = userService.getByPseudo(username);
        if (query != null){
            return new User(username, query.getMdp(), new ArrayList<>());
        } else throw new UsernameNotFoundException("User not found with username " + username);
    }
}
