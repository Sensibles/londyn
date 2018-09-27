package pl.arturkufa.londynsecurity.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;
    @Value("${jwt.header}")
    private String headerString;
    @Value("${jwt.expirationDate}")
    private long expirationTime;

    public String getSecret() {
        return secret;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public String getHeaderString() {
        return headerString;
    }

    public long getExpirationTime() {
        return expirationTime;
    }
}
