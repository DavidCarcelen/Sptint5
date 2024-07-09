package dice.game.david.carcelen.model.services;

import dice.game.david.carcelen.model.dtos.GameDTO;
import dice.game.david.carcelen.model.dtos.PlayerDTO;

import java.util.List;

public interface GameService {
    void newGame(long idPlayer);
   /* void updateGame(GameDTO gameDTO);
    void deleteGame(String id);*/
    GameDTO getOneGame(String id);
    List<GameDTO> getAllGames(long idPlayer);
}
