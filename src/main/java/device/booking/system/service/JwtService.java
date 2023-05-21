package device.booking.system.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret.key}")
    private String jwtSecretKey;
    @Value("${jwt.secret.expiry}")
    private long expiry;
    @Value("${jwt.secret.issuer}")
    private String issuer;

    public String createToken(long id, String email) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(String.valueOf(id))
                .setIssuedAt(new Date())
                .setSubject(email)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        builder.setExpiration(new Date(System.currentTimeMillis() + expiry * 1000));

        return builder.compact();
    }

    public Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
                .parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(Claims claims) {
        return getClaimFromToken(claims, Claims::getSubject);
    }

    public String getUserIdFromToken(Claims claims) {
        return getClaimFromToken(claims, Claims::getId);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(Claims claims) {
        return getClaimFromToken(claims, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(Claims claims, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(claims);
    }

    // check if the token has expired
    public boolean isTokenExpired(Claims claims) {
        final Date expiration = getExpirationDateFromToken(claims);
        return expiration.before(new Date());
    }
}
