package pl.arturkufa.londynsecurity.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import pl.arturkufa.londynsecurity.model.ApplicationUser;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService userDetailsService;
    private SecurityConstants securityConstants;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, SecurityConstants securityConstant) {
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
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //TODO FIX THIS WHEN SECURITY START WORKING. AVOID USING 'ApplicationUser', this class is not necessary.
        ApplicationUser applicationUser = new ApplicationUser(userDetails.getUsername(), userDetails.getPassword());
        return username != null
                ? new UsernamePasswordAuthenticationToken(applicationUser, null, userDetails.getAuthorities())
                : null;
    }
}

