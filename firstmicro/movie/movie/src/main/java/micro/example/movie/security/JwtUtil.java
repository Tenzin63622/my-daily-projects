package micro.example.movie.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    // ⚠️ MUST MATCH auth service secret
    private final String secret = "###";

    private Key getKey() {
        return Keys.hmacShaKeyFor(java.util.Base64.getDecoder().decode(secret));
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}