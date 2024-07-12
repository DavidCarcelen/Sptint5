package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.model.domain.Game;
import dice.game.david.carcelen.model.dtos.GameDTO;
import dice.game.david.carcelen.model.mappers.GameMapper;
import dice.game.david.carcelen.model.repository.mongo.GameRepo;
import dice.game.david.carcelen.model.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepo gameRepo;
    @Override
    public void newGame(long idPlayer) {
        Game game = new Game(idPlayer);
        gameRepo.save(game);

    }

    @Override
    public void deleteAllGames(long id) {
        gameRepo.deleteByIdPlayer(id);

    }

    @Override
    public GameDTO getOneGame(String id) {
        return null;
    }

    @Override
    public List<GameDTO> getAllGames(long idPlayer) {
        return gameRepo.findByIdPlayer(idPlayer).stream()
                .map(GameMapper::toDTO)
                .collect(Collectors.toList());
    }
}
