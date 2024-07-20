package dice.game.david.carcelen.model.services;

import dice.game.david.carcelen.dao.AuthResponse;
import dice.game.david.carcelen.dao.AuthenticationRequest;
import dice.game.david.carcelen.dao.RegisterRequest;
import dice.game.david.carcelen.model.dtos.PlayerDTO;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse authenticate(AuthenticationRequest request);
}
