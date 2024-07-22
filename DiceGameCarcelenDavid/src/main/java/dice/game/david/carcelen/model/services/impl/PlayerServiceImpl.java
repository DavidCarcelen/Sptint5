package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.exceptions.BadIdMatchException;
import dice.game.david.carcelen.exceptions.IdNotFoundException;
import dice.game.david.carcelen.exceptions.NameNotAvailableException;
import dice.game.david.carcelen.exceptions.PlayerNotFoundException;
import dice.game.david.carcelen.model.domain.Game;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.domain.Role;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.mappers.PlayerMapper;
import dice.game.david.carcelen.model.repository.mongo.GameRepo;
import dice.game.david.carcelen.model.repository.jpa.PlayerRepo;
import dice.game.david.carcelen.model.services.JWTService;
import dice.game.david.carcelen.model.services.PlayerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepo playerRepo;
    private final GameRepo gameRepo;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    public PlayerServiceImpl(PlayerRepo playerRepo, GameRepo gameRepo, PasswordEncoder passwordEncoder) {
        this.playerRepo = playerRepo;
        this.gameRepo = gameRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public synchronized void createAdminUserIfNotExists() {
        Optional<Player> admin = playerRepo.findByEmail(adminEmail);

        if (admin.isEmpty()) {
            Player adminUser = Player.builder()
                    .email(adminEmail)
                    .name("admin")
                    .password(passwordEncoder.encode(adminPassword))
                    .role(Role.ADMIN)
                    .signDate(new Date())
                    .build();

            playerRepo.save(adminUser);
        }
    }

    @Override
    public void updatePlayer(long idPlayer, PlayerDTO playerDTO) {

        Player playerToUpdate = playerRepo.findById(idPlayer)
                .orElseThrow(() -> new IdNotFoundException("Player with ID " + idPlayer + " not found."));
        if(!playerToUpdate.getEmail().equals(playerDTO.getEmail())){
            throw new BadIdMatchException("Can't update other players. ");
        }
        checkName(playerDTO.getName());
        playerToUpdate.setName(playerDTO.getName());
        playerRepo.save(playerToUpdate);
    }

    @Override
    public void deletePlayer(long id) {
        if (!playerRepo.existsById(id)) {
            throw new IdNotFoundException("Player with ID " + id + " not found.");
        }
        playerRepo.deleteById(id);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepo.findAll().stream().map(player -> {
            List<Game> games = gameRepo.findByIdPlayer(player.getId());
            return PlayerMapper.toDTO(player, games);
        }).collect(Collectors.toList());
    }

    @Override
    public String getAverageRate(List<PlayerDTO> players) {
        double averageWinRate = players.stream().mapToDouble(PlayerDTO::getWinRate).average().orElse(0.0);
        return String.format("Average Win Rate: %.2f", averageWinRate);
    }

    @Override
    public PlayerDTO getWinner(List<PlayerDTO> players) {
        double highestWinRate = 0;
        PlayerDTO winner = null;
        for (PlayerDTO playerDTO : players) {
            playerDTO.setWinRate(gameRepo.findByIdPlayer(playerDTO.getId()));
            if (playerDTO.getWinRate() > highestWinRate) {
                highestWinRate = playerDTO.getWinRate();
                winner = playerDTO;
            }
        }
        return winner;
    }

    @Override
    public PlayerDTO getLoser(List<PlayerDTO> players) {
        double lowestWinRate = Double.MAX_VALUE;
        PlayerDTO loser = null;
        for (PlayerDTO playerDTO : players) {
            playerDTO.setWinRate(gameRepo.findByIdPlayer(playerDTO.getId()));
            if (playerDTO.getWinRate() < lowestWinRate) {
                lowestWinRate = playerDTO.getWinRate();
                loser = playerDTO;
            }
        }
        return loser;
    }

    private void checkName(String name) {
        if (!name.isBlank()) {
            playerRepo.findByName(name).ifPresent(player -> {
                throw new NameNotAvailableException("Name not available, try another name");
            });
        }
    }
}
