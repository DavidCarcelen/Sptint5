package dice.game.david.carcelen.model.mappers;

import dice.game.david.carcelen.model.domain.Game;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class PlayerMapper {

    private static PasswordEncoder passwordEncoder;

    public static Player toEntity (PlayerDTO playerDTO){
        String name = playerDTO.getName();
        if (name.equals("ANONYMOUS")) name = "";
        return new Player(playerDTO.getEmail(), passwordEncoder.encode(playerDTO.getPassword()), name);
    }

    public static PlayerDTO toDTO (Player player, List<Game> games){
        PlayerDTO playerDTO = new PlayerDTO(player.getEmail(), player.getPassword(), player.getName());
        if (player.getName().isEmpty()) playerDTO.setName("ANONYMOUS");
        playerDTO.setId(player.getId());
        playerDTO.setSignDate(player.getSignDate());
        playerDTO.setWinRate(games);
        return playerDTO;
    }
}
