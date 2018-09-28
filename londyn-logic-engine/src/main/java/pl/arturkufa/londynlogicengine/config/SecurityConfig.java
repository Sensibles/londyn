package pl.arturkufa.londynlogicengine.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.arturkufa.londyncommon.security.config.JwtAuthenticationFilter;
import pl.arturkufa.londyncommon.security.config.JwtAuthorizationFilter;
import pl.arturkufa.londyncommon.security.config.SecurityConstants;
import pl.arturkufa.londyncommon.security.service.DatabaseUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled =  false)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DatabaseUserDetailsService userDetailsService;
    @Autowired
    private SecurityConstants securityConstants;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/hello", "/sendKafkaMessage").hasRole("USER")
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), securityConstants))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService, securityConstants));
        System.out.println("ENCODED PASSWORD: " + passwordEncoder.encode("password"));

    }
}
