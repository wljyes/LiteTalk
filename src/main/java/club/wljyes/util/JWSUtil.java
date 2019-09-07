package club.wljyes.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JWSUtil {
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createToken(Map<String, Object> claims) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, 1);
        Date expireDate = now.getTime();
        String token = Jwts.builder().setClaims(claims).setIssuer("wljyes").
                setAudience("webUser").setExpiration(expireDate).signWith(key).compact();
        return token;
    }

    public static Jws<Claims> parseToken(String token) {
        Jws<Claims> jws = null;
        try {
            jws = Jwts.parser().requireAudience("webUser").setSigningKey(key).parseClaimsJws(token);
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return jws;
    }
}
