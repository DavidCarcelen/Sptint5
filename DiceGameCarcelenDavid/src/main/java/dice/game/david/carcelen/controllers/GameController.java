package dice.game.david.carcelen.controllers;

import dice.game.david.carcelen.model.dtos.GameDTO;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.services.GameService;
import dice.game.david.carcelen.model.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @PostMapping("/newGame/{idPlayer}")
    public ResponseEntity<String> newGame(@PathVariable long idPlayer) {
        GameDTO gameDTO = gameService.newGame(idPlayer);
        String result = gameDTO.isWin()?"You Win!!":"You lose.";
        return ResponseEntity.ok(result + "\n " + gameDTO.getValueDie1() + " + " + gameDTO.getValueDie2());
    }

    @DeleteMapping("/deleteAllGames/{idPlayer}")
    public ResponseEntity<String> deleteAllGames(@PathVariable long idPlayer) {
        gameService.deleteAllGames(idPlayer);
        PlayerDTO playerDTO = playerService.getOnePlayer(idPlayer);
        return ResponseEntity.ok("All games deleted for player: " + playerDTO.getName());
    }

    @GetMapping("/getOneGame/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable String id) {
        GameDTO gameDTO = gameService.getOneGame(id);
        return ResponseEntity.ok(gameDTO);
    }

    @GetMapping("/getAllGames/{idPlayer}")
    public ResponseEntity<List<GameDTO>> getAllGames(@PathVariable long idPlayer) {
        List<GameDTO> games = gameService.getAllGames(idPlayer);
        return ResponseEntity.ok(games);
    }
}
