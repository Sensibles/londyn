package pl.arturkufa.londynsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.arturkufa.londynsecurity.model.entity.User;
import pl.arturkufa.londynsecurity.model.mapper.UserMap;

@Component
public class DatabaseUserDetailsServiceImpl implements DatabaseUserDetailsService {

    @Autowired
    private UserMap userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = loadDatabaseUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole()));
    }

    public User loadDatabaseUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
