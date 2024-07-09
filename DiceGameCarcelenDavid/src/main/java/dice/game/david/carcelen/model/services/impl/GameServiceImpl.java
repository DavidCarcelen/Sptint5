package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.model.domain.Game;
import dice.game.david.carcelen.model.dtos.GameDTO;
import dice.game.david.carcelen.model.repository.GameRepo;
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
/*
    @Override
    public void updateGame(GameDTO gameDTO) {

    }

    @Override
    public void deleteGame(String id) {

    }*/

    @Override
    public GameDTO getOneGame(String id) {
        return null;
    }

    @Override
    public List<GameDTO> getAllGames(long idPlayer) {
        return gameRepo.findByIdPlayer(idPlayer).stream()
                .map(game -> {
                    GameDTO gameDTO = new GameDTO();
                    gameDTO.setId(game.getId());
                    gameDTO.setDie1(game.getValueDie1());
                    gameDTO.setDie2(game.getValueDie2());
                    gameDTO.setWin(game.isWin());
                    return gameDTO;
                }).collect(Collectors.toList());
    }
}
