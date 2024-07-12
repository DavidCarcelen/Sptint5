package dice.game.david.carcelen.model.mappers;

import dice.game.david.carcelen.model.domain.Game;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.GameDTO;
import dice.game.david.carcelen.model.dtos.PlayerDTO;

import java.util.List;

public class GameMapper {

    public static Game toEntity (GameDTO gameDTO){
        return new Game(gameDTO.getIdPlayer());
    }

    public static GameDTO toDTO (Game game){
        GameDTO gameDTO = new GameDTO(game.getIdPlayer());
        gameDTO.setValueDie1(game.getValueDie1());
        gameDTO.setValueDie2(game.getValueDie2());
        gameDTO.setWin(game.isWin());
        gameDTO.setId(gameDTO.getId());
        return gameDTO;
    }

}
