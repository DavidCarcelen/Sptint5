package dice.game.david.carcelen.controllers;

import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/add")
    public ResponseEntity<String> addPlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.addPlayer(playerDTO);
        return ResponseEntity.ok("player added");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.updatePlayer(playerDTO);
        return ResponseEntity.ok("player updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok("Player deleted");
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable int id) {
        PlayerDTO playerDTO = playerService.getOnePlayer(id);
        return ResponseEntity.ok(playerDTO);
    }

    @GetMapping("/getAll")
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


}
