package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.dao.AuthResponse;
import dice.game.david.carcelen.dao.AuthenticationRequest;
import dice.game.david.carcelen.dao.RegisterRequest;
import dice.game.david.carcelen.exceptions.NameNotAvailableException;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.domain.Role;
import dice.game.david.carcelen.model.repository.jpa.PlayerRepo;
import dice.game.david.carcelen.model.services.AuthService;
import dice.game.david.carcelen.model.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
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
        playerRepo.findByName(request.getName())
                .ifPresent(user -> {
                    throw new NameNotAvailableException("Name already registered:" + user.getName());
                });
        Player player = Player.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        playerRepo.save(player);
        String jwToken = jwtService.generateToken(player);
        return AuthResponse.builder().token(jwToken).build();
    }

    @Override
    public AuthResponse authentication(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        Player player = playerRepo.findByName(request.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + request.getName()));
        String jwt = jwtService.generateToken(player);
        return AuthResponse.builder().token(jwt).build();
    }
}
