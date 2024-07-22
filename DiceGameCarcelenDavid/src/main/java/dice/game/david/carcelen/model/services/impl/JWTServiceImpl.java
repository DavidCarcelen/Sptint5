package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.exceptions.BadIdMatchException;
import dice.game.david.carcelen.exceptions.PlayerNotFoundException;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.repository.jpa.PlayerRepo;
import dice.game.david.carcelen.model.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    @Autowired
    private PlayerRepo playerRepo;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Override
    public String getUserName(String token) {
        return getClaim(token, Claims::getSubject);
    }


    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        extraClaims.put("role", userDetails.getAuthorities().stream()
                .map(authority -> "ROLE_" + authority.getAuthority())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User has no roles")));

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);

    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    @Override
    public void checkId(long idPlayer, String authHeader) {
        String token = authHeader.substring(7);
        String role = getRoleFromToken(token);
        if (!role.equals("ROLE_ADMIN")) {
            String userEmail = getUserName(token);
            Optional<Player> repoPlayer = playerRepo.findById(idPlayer);
            if (repoPlayer.isEmpty() || !userEmail.equals(repoPlayer.get().getEmail())) {
                throw new BadIdMatchException("Forbidden: Can't access to other players ID");
            }
        }

    }

    @Override
    public String getRoleFromToken(String token) {
        return getClaim(token, claims -> claims.get("role", String.class));
    }

}
