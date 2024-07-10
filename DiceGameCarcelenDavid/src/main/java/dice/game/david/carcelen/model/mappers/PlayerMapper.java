package dice.game.david.carcelen.model.mappers;

import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.PlayerDTO;

public class PlayerMapper {

    public static Player toEntity (PlayerDTO playerDTO){
        return new Player(playerDTO.getName());
    }

    public static PlayerDTO toDTO (Player player){
        PlayerDTO playerDTO = new PlayerDTO(player.getName());
        playerDTO.setId(player.getId());
        playerDTO.setSignDate(player.getSignDate());
        return playerDTO;
    }
}
