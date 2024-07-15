package dice.game.david.carcelen.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Entity
@Data
@NoArgsConstructor
public class Game {
    @Id
    private String id;
    private int valueDie1;
    private int valueDie2;
    private boolean win;
    private long idPlayer;

    public Game(long idPlayer) {
        this.idPlayer = idPlayer;
        this.valueDie1 = new Random().nextInt(6) + 1;
        this.valueDie2 = new Random().nextInt(6) + 1;
        this.win = (this.valueDie1 + this.valueDie2) == 7;
    }

}
