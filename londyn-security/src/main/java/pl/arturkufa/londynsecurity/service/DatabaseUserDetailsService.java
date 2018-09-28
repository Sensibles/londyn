package pl.arturkufa.londynsecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.arturkufa.londynsecurity.model.entity.User;

public interface DatabaseUserDetailsService extends UserDetailsService {
    User loadDatabaseUserByUsername(String username);
}
