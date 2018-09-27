package pl.arturkufa.londynmain.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/index").permitAll()
                .antMatchers("/hello", "/auth/**").access("hasRole('ROLE_USER')")
                .and()
                .formLogin()
                .loginPage("/login")      //adding custom login path by specifying path
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/login?logout")
                ).logoutSuccessUrl("/logout").permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled"
                        + " from users where username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.rolename from " +
                        "USERS u LEFT JOIN ROLES r ON u.ROLEID = r.ROLEID where u.username = ?")
                .passwordEncoder(passwordEncoder);
        System.out.println("ENCODED PASSWORD: " + passwordEncoder.encode("password"));
    }



}
