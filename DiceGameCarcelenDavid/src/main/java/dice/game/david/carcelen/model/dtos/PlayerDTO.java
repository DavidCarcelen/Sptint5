package dice.game.david.carcelen.model.dtos;


import dice.game.david.carcelen.model.domain.Game;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PlayerDTO {
    private long id;
    private Date signDate;
    private String name;
    private double winRate;
    private String email;

    public PlayerDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void setWinRate(List<Game> games) {
        long totalGames = games.size();
        long wins = games.stream().filter(Game::isWin).count();
        this.winRate = totalGames == 0 ? 0 : (double) wins / totalGames * 100;
    }
}
