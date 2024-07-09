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

    @PostMapping("/newGame/{id}")
    public ResponseEntity<String> newGame(@PathVariable long idPlayer) {
        gameService.newGame(idPlayer);
        return ResponseEntity.ok("game added");
    }
    /*
    @PutMapping("/update")
    public ResponseEntity<String> updatePlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.updatePlayer(playerDTO);
        return ResponseEntity.ok("player updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok("Player deleted");
    }*/

    @GetMapping("/getOneGame/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable String id) {
        GameDTO gameDTO = gameService.getOneGame(id);
        return ResponseEntity.ok(gameDTO);
    }

    @GetMapping("/getAllGames/{id}")
    public ResponseEntity<List<GameDTO>> getAllGames(@PathVariable long idPlayer) {
        List<GameDTO> games = gameService.getAllGames(idPlayer);
        return ResponseEntity.ok(games);
    }
}
