package dice.game.david.carcelen.model.mappers;

import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.PlayerDTO;

public class PlayerMapper {

    public static Player toEntity (PlayerDTO playerDTO){
        String name = playerDTO.getName();
        if (name.equals("ANONYMOUS")) name = "";
        return new Player(name);
    }

    public static PlayerDTO toDTO (Player player){
        PlayerDTO playerDTO = new PlayerDTO(player.getName());
        if (player.getName().isEmpty()) playerDTO.setName("ANONYMOUS");
        playerDTO.setId(player.getId());
        playerDTO.setSignDate(player.getSignDate());
        return playerDTO;
    }
}
