package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.exceptions.IdNotFoundException;
import dice.game.david.carcelen.model.domain.Game;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.repository.GameRepo;
import dice.game.david.carcelen.model.repository.PlayerRepo;
import dice.game.david.carcelen.model.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private GameRepo gameRepo;

    @Override
    public void addPlayer(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO.getName());
        playerRepo.save(player);

    }

    @Override
    public void updatePlayer(PlayerDTO playerDTO) {
        Player playerToUpdate = playerRepo.findById(playerDTO.getId())
                .orElseThrow(() -> new IdNotFoundException("Player with ID " + playerDTO.getId() + " not found."));
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
    public PlayerDTO getOnePlayer(long id) {
        Player player = playerRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Player with ID " + id + " not found."));
        PlayerDTO playerDTO = new PlayerDTO(player.getName());
        playerDTO.setId(id);

        List<Game> games = gameRepo.findByIdPlayer(id);
        long totalGames = games.size();
        long wins = games.stream().filter(Game::isWin).count();
        double winRate = totalGames == 0 ? 0 : (double) wins / totalGames * 100;
        playerDTO.setWinRate(winRate);
        return playerDTO;
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepo.findAll().stream()
                .map(player -> {
                    PlayerDTO playerDTO = new PlayerDTO(player.getName());
                    playerDTO.setId(player.getId());
                    return playerDTO;
                }).collect(Collectors.toList());
    }
}
