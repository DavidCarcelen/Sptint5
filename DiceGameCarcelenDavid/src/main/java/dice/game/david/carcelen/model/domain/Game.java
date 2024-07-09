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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private int Die1;
    private int Die2;
    private boolean win;
    private long idPlayer;

    public Game(long idPlayer) {
        this.idPlayer = idPlayer;
        this.Die1 = new Random().nextInt(6) + 1;
        this.Die2 = new Random().nextInt(6) + 1;
        this.win = (this.Die1 + this.Die2) == 7;
    }

}
