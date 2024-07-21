package dice.game.david.carcelen.model.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {

    String getUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
    void checkId(long id, String token);
    String getRoleFromToken(String token);


}
