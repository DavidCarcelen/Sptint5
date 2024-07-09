package dice.game.david.carcelen.model.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class PlayerDTO {
    private long id;
    private Date signDate;
    private String name;
    private double winRate;

    public PlayerDTO(String name) {
        this.name = name;
    }
}
