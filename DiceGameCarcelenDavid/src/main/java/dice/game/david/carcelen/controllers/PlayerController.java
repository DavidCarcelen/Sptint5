package dice.game.david.carcelen.controllers;

import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.services.JWTService;
import dice.game.david.carcelen.model.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private JWTService jwtService;

    @PutMapping("/users/{idPlayer}")
    public ResponseEntity<String> updatePlayer(@PathVariable long idPlayer, @RequestBody PlayerDTO playerDTO, @RequestHeader("Authorization") String authHeader) {
        jwtService.checkId(idPlayer, authHeader);
        playerService.updatePlayer(playerDTO);
        return ResponseEntity.ok("player updated");
    }

    @DeleteMapping("/users/delete/{idPlayer}")
    public ResponseEntity<String> deletePlayer(@PathVariable long idPlayer, @RequestHeader("Authorization") String authHeader) {
        jwtService.checkId(idPlayer, authHeader);
        playerService.deletePlayer(idPlayer);
        return ResponseEntity.ok("Player deleted");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/getAverageRate")
    public ResponseEntity<String> getAverageRate() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        String result = playerService.getAverageRate(players);
        return ResponseEntity.ok(result);
    }

    @GetMapping("users/ranking/winner")
    public ResponseEntity<PlayerDTO> getWinner() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        PlayerDTO winner = playerService.getWinner(players);
        return ResponseEntity.ok(winner);
    }

    @GetMapping("users/ranking/loser")
    public ResponseEntity<PlayerDTO> getLoser() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        PlayerDTO loser = playerService.getLoser(players);
        return ResponseEntity.ok(loser);
    }


}
