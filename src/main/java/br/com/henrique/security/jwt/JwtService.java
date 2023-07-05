package br.com.henrique.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.henrique.entities.UserAuthenticate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    @Value("${security.jwt.expiracao}")
    private String expirated;
    @Value(("${security.jwt.chave-assinatura}"))
    private String keySignature;

    public String generateToken(UserAuthenticate user){
        long expString = Long.valueOf(expirated);
        LocalDateTime dateTimeExpiration = LocalDateTime.now().plusMinutes(expString); // pega a data e soma com os minutos, no caso, 30
        Date date = Date.from(dateTimeExpiration.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, keySignature)
                .compact();

    }

    private Claims getClaims(String token) throws ExpiredJwtException {
       return Jwts.parser()
                .setSigningKey(keySignature)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValidated(String token){
        try{
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime localDateTime = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);

        }catch (Exception ex){
            return false;
        }
    }

    public String getUserLogin(String token) throws ExpiredJwtException{
        return (String) getClaims(token).getSubject();

    }
}
