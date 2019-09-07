package club.wljyes.util;

import club.wljyes.bean.User;
import com.google.gson.JsonDeserializer;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.JacksonDeserializer;
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
        return parseToken(token, null);
    }

    public static Jws<Claims> parseToken(String token, JacksonDeserializer<Map<String, ?>> deserializer) {
        Jws<Claims> jws = null;
        try {
            if (deserializer == null) {
                jws = Jwts.parser().requireAudience("webUser").setSigningKey(key).parseClaimsJws(token);
            } else {
                jws = Jwts.parser().requireAudience("webUser").deserializeJsonWith(deserializer)
                        .setSigningKey(key).parseClaimsJws(token);
            }
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return jws;
    }
}
