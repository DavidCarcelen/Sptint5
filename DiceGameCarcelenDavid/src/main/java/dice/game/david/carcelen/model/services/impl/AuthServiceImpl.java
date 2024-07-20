package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.dao.AuthResponse;
import dice.game.david.carcelen.dao.AuthenticationRequest;
import dice.game.david.carcelen.dao.RegisterRequest;
import dice.game.david.carcelen.exceptions.NameNotAvailableException;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.domain.Role;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.mappers.PlayerMapper;
import dice.game.david.carcelen.model.repository.jpa.PlayerRepo;
import dice.game.david.carcelen.model.services.AuthService;
import dice.game.david.carcelen.model.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PlayerRepo playerRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

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
        Player player = Player.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .signDate(new Date())
                .role(Role.USER)
                .build();
        //Player player = new Player(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getName());
        playerRepo.save(player);
        String jwtToken = jwtService.generateToken(player);
        //String jwToken = jwtService.generateToken(player);
        return AuthResponse.builder().token(jwtToken).build();
    }


    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Player player = playerRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));
        String jwt = jwtService.generateToken(player);
        return AuthResponse.builder().token(jwt).build();
    }
}
