package com.leonde.seconddawn.security;


import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


@Component
public class JwtProvider {

    private static final long EXPIRATION_TIME = 5;

    // Secret key used to sign the JWT
    private String secretKey;

    public String resolveToken(HttpServletRequest request) {
        // Extract the token from the request headers
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            // Verify the token using the secret key
            Jwts.
            parser().
            setSigningKey(secretKey).
            parseClaimsJws(token);

            return true;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        // Extract the user's information from the token
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        String username = claims.getSubject();

        List<String> roles = claims.get("roles", List.class);

        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // Create the user's authentication
        User user = new User(username, "", authorities);
        return new UsernamePasswordAuthenticationToken(user, "", authorities);
    }

    public String generateToken(Authentication auth) {
        // Extract the user's information
        User user = (User) auth.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Build the JWT token
        return Jwts.builder().
                setSubject(user.getUsername()).
                claim("roles", roles).
                setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
                signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }
}



