package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.exceptions.IdNotFoundException;
import dice.game.david.carcelen.model.domain.Game;
import dice.game.david.carcelen.model.dtos.GameDTO;
import dice.game.david.carcelen.model.mappers.GameMapper;
import dice.game.david.carcelen.model.repository.jpa.PlayerRepo;
import dice.game.david.carcelen.model.repository.mongo.GameRepo;
import dice.game.david.carcelen.model.services.GameService;
import dice.game.david.carcelen.model.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private PlayerRepo playerRepo;

    @Override
    public GameDTO newGame(long idPlayer) {
        checkPlayerId(idPlayer);
        Game game = new Game(idPlayer);
        gameRepo.save(game);
        return GameMapper.toDTO(game);

    }

    @Override
    public void deleteAllGames(long idPlayer) {
        checkPlayerId(idPlayer);
        gameRepo.deleteByIdPlayer(idPlayer);

    }

    @Override
    public List<GameDTO> getAllGames(long idPlayer) {
        checkPlayerId(idPlayer);
        return gameRepo.findByIdPlayer(idPlayer).stream()
                .map(GameMapper::toDTO)
                .collect(Collectors.toList());
    }

    private void checkPlayerId (long idPlayer){
        playerRepo.findById(idPlayer).orElseThrow(()-> new IdNotFoundException("Player not found with id " + idPlayer));
    }
}
