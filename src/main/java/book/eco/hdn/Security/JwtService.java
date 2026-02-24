package book.eco.hdn.Security;

import book.eco.hdn.Model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "hdn-super-secret-key-2026-hdn-super-secret-key-2026";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    private final long ACCESS_EXPIRE = 1000 * 60 * 15; // 15 phút
    private final long REFRESH_EXPIRE = 1000L * 60 * 60 * 24 * 7; // 7 ngày

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("roles", user.getRoles()
                        .stream()
                        .map(role -> role.getName())
                        .toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRE))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRE))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}