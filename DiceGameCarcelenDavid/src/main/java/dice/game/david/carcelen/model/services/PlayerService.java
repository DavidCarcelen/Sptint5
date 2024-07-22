package dice.game.david.carcelen.model.services;

import dice.game.david.carcelen.model.dtos.PlayerDTO;

import java.util.List;

public interface PlayerService {
    void updatePlayer(long idPlayer, PlayerDTO playerDTO);

    void deletePlayer(long idPlayer);

    List<PlayerDTO> getAllPlayers();

    String getAverageRate(List<PlayerDTO> players);

    PlayerDTO getWinner(List<PlayerDTO> players);

    PlayerDTO getLoser(List<PlayerDTO> players);

}
