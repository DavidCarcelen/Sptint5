package dice.game.david.carcelen.controllers;

import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.services.JWTService;
import dice.game.david.carcelen.model.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private JWTService jwtService;

    @PutMapping()
    public ResponseEntity<String> updatePlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.updatePlayer(playerDTO);
        return ResponseEntity.ok("player updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable long idPlayer, @RequestHeader("Authorization") String authHeader) {
        jwtService.checkId(idPlayer, authHeader);
        playerService.deletePlayer(idPlayer);
        return ResponseEntity.ok("Player deleted");
    }

    @GetMapping()
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping("/getAverageRate")
    public ResponseEntity<String> getAverageRate() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        String result = playerService.getAverageRate(players);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/ranking/winner")
    public ResponseEntity<PlayerDTO> getWinner() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        PlayerDTO winner = playerService.getWinner(players);
        return ResponseEntity.ok(winner);
    }

    @GetMapping("/ranking/loser")
    public ResponseEntity<PlayerDTO> getLoser() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        PlayerDTO loser = playerService.getLoser(players);
        return ResponseEntity.ok(loser);
    }


}
