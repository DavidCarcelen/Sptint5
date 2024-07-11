package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.exceptions.IdNotFoundException;
import dice.game.david.carcelen.exceptions.NameNotAvailableException;
import dice.game.david.carcelen.model.domain.Game;
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
        checkName(playerDTO.getName());
        playerRepo.save(PlayerMapper.toEntity(playerDTO));

    }

    @Override
    public void updatePlayer(PlayerDTO playerDTO) {
        Player playerToUpdate = playerRepo.findById(playerDTO.getId())
                .orElseThrow(() -> new IdNotFoundException("Player with ID " + playerDTO.getId() + " not found."));
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
    public PlayerDTO getOnePlayer(long id) {
        Player player = playerRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Player with ID " + id + " not found."));
        List<Game> games = gameRepo.findByIdPlayer(id);
        return PlayerMapper.toDTO(player, games);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepo.findAll().stream()
                .map(player -> {
                    List<Game> games = gameRepo.findByIdPlayer(player.getId());
                    return PlayerMapper.toDTO(player, games);
                })
                .collect(Collectors.toList());
    }


    private void checkName(String name) {
        if (!name.isBlank()) {
            playerRepo.findByName(name).ifPresent(player -> {
                throw new NameNotAvailableException("Name not available, try another name");
            });
        }
    }
    @Override
    public String getAverageRate(List<PlayerDTO> players) {
        double averageWinRate = players.stream()
                .mapToDouble(PlayerDTO::getWinRate)
                .average()
                .orElse(0.0);
        return String.format("Average Win Rate: %.2f", averageWinRate);
    }

}
