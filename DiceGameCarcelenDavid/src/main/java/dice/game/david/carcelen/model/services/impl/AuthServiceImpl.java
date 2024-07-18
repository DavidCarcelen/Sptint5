package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.dao.AuthResponse;
import dice.game.david.carcelen.dao.AuthenticationRequest;
import dice.game.david.carcelen.dao.RegisterRequest;
import dice.game.david.carcelen.exceptions.NameNotAvailableException;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.mappers.PlayerMapper;
import dice.game.david.carcelen.model.repository.jpa.PlayerRepo;
import dice.game.david.carcelen.model.services.AuthService;
import dice.game.david.carcelen.model.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("User password cannot be null");
        }
        playerRepo.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new NameNotAvailableException("email already registered:" + user.getEmail());
                });
        if (!request.getName().isBlank()){
            playerRepo.findByName(request.getName())
                    .ifPresent(user -> {
                        throw new NameNotAvailableException("name not available:" + user.getName());
                    });
        }
        Player player = new Player(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getName());
        playerRepo.save(player);
        String jwToken = jwtService.generateToken(player);
        return AuthResponse.builder().token(jwToken).build();
    }


    @Override
    public AuthResponse authentication(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Player player = playerRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));
        String jwt = jwtService.generateToken(player);
        return AuthResponse.builder().token(jwt).build();
    }
}
