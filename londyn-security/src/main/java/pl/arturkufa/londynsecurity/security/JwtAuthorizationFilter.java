package pl.arturkufa.londynsecurity.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.arturkufa.londynsecurity.model.entity.User;
import pl.arturkufa.londynsecurity.service.DatabaseUserDetailsService;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private DatabaseUserDetailsService userDetailsService;
    private SecurityConstants securityConstants;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, DatabaseUserDetailsService userDetailsService, SecurityConstants securityConstant) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.securityConstants = securityConstant;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(securityConstants.getHeaderString());
        if(Objects.isNull(header) || !header.startsWith(securityConstants.getTokenPrefix())){
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuth = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(securityConstants.getHeaderString());
        String username = Jwts.parser().setSigningKey(securityConstants.getSecret())
                .parseClaimsJws(token.replace(securityConstants.getTokenPrefix(), ""))
                .getBody()
                .getSubject();
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        User user = userDetailsService.loadDatabaseUserByUsername(username);
        return username != null
                ? new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(user.getRole()))
                : null;
    }
}

