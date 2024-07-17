package dice.game.david.carcelen.model.services;

import dice.game.david.carcelen.dao.AuthResponse;
import dice.game.david.carcelen.dao.AuthenticationRequest;
import dice.game.david.carcelen.dao.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse authentication(AuthenticationRequest request);
}
