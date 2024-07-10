package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.exceptions.IdNotFoundException;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.mappers.PlayerMapper;
import dice.game.david.carcelen.model.repository.mongo.GameRepo;
import dice.game.david.carcelen.model.repository.jpa.PlayerRepo;
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
        playerRepo.save(PlayerMapper.toEntity(playerDTO));

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
        PlayerDTO playerDTO = PlayerMapper.toDTO(player);

        /*List<Game> games = gameRepo.findByIdPlayer(id);
        long totalGames = games.size();
        long wins = games.stream().filter(Game::isWin).count();
        double winRate = totalGames == 0 ? 0 : (double) wins / totalGames * 100;
        playerDTO.setWinRate(winRate);*/
        return playerDTO;
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepo.findAll().stream()
                .map(PlayerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
