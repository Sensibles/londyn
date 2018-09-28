package pl.arturkufa.londyncommon.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.arturkufa.londyncommon.security.model.entity.User;

public interface DatabaseUserDetailsService extends UserDetailsService {
    User loadDatabaseUserByUsername(String username);
}
