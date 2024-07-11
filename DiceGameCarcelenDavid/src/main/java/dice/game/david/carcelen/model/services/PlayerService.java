package dice.game.david.carcelen.model.services;

import dice.game.david.carcelen.model.dtos.PlayerDTO;

import java.util.List;

public interface PlayerService {
    void addPlayer(PlayerDTO playerDTO);
    void updatePlayer(PlayerDTO playerDTO);
    void deletePlayer(long id);
    PlayerDTO getOnePlayer(long id);
    List<PlayerDTO> getAllPlayers();
    String getAverageRate(List<PlayerDTO> players);
}
