package dice.game.david.carcelen.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class GameDTO implements Serializable {
    private String id;
    private int Die1;
    private int Die2;
    private boolean win;
    private long idPlayer;

    public GameDTO(long idPlayer){
        this.idPlayer = idPlayer;
    }
}
