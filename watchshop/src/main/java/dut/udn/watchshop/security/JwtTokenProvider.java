package dut.udn.watchshop.config;

import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
    @Value("ThisIsASescret")
    private String jwtSecret;

    @Value("864000000")
    private int jwtExpirationInMs;

    public String getUsernameFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Date getIssuedAtDateFromToken(String token) {
        return extractClaims(token).getIssuedAt();
    }

    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        Date createdDate = new Date();
        Date expirationDate = new Date((System.currentTimeMillis() + jwtExpirationInMs));
        System.out.println(expirationDate);

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
                .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String createToken(String username, Date issueDate) {
        String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(issueDate)
                .setExpiration(new Date(issueDate.getTime() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return jwtToken;
    }

    public String getSubject(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    public String refreshToken(String token, long expirationInSeconds) {
        final Claims claims = extractClaims(token);

        Date now = new Date();
        claims.setIssuedAt(now);
        claims.setExpiration(new Date(now.getTime() + jwtExpirationInMs));

        return createTokenFromClaims(claims);
    }

    public boolean isTokenExpired(String token) {
        final Claims claims = extractClaims(token);
        Date now = new Date();

        return now.after(claims.getExpiration());
    }

    private String createTokenFromClaims(Claims claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
}
