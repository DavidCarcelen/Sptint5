package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.exceptions.IdNotFoundException;
import dice.game.david.carcelen.exceptions.NameNotAvailableException;
import dice.game.david.carcelen.exceptions.PlayerNotFoundException;
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

    /*
    @Override
    public void addPlayer(PlayerDTO playerDTO) {
        checkName(playerDTO.getName());
        playerRepo.save(PlayerMapper.toEntity(playerDTO));

    }*/

    @Override
    public void updatePlayer(PlayerDTO playerDTO) {
        Player playerToUpdate = playerRepo.findByEmail(playerDTO.getEmail()).orElseThrow(() -> new PlayerNotFoundException("Player with email " + playerDTO.getEmail() + " not found."));
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
        double HighestWinrate = 0;
        PlayerDTO winner = null;
        for (PlayerDTO playerDTO : players) {
            playerDTO.setWinRate(gameRepo.findByIdPlayer(playerDTO.getId()));
            if (playerDTO.getWinRate() < HighestWinrate) {
                HighestWinrate = playerDTO.getWinRate();
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
