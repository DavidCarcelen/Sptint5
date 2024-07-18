package dice.game.david.carcelen.controllers;

import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.GameDTO;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.services.GameService;
import dice.game.david.carcelen.model.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @PostMapping("/{idPlayer}/games")
    public ResponseEntity<String> newGame(@PathVariable long idPlayer) {
        GameDTO gameDTO = gameService.newGame(idPlayer);
        String result = gameDTO.isWin()?"You Win!!":"You lose.";
        return ResponseEntity.ok(result + "\n " + gameDTO.getValueDie1() + " + " + gameDTO.getValueDie2());
    }

    @DeleteMapping("/{idPlayer}/games")
    public ResponseEntity<String> deleteAllGames(@PathVariable long idPlayer) {
        gameService.deleteAllGames(idPlayer);
        return ResponseEntity.ok("All games deleted for player with id: " + idPlayer);
    }


    @GetMapping("/{idPlayer}/games")
    public ResponseEntity<List<GameDTO>> getAllGames(@PathVariable long idPlayer) {
        List<GameDTO> games = gameService.getAllGames(idPlayer);
        return ResponseEntity.ok(games);
    }
}
