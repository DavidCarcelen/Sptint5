package dice.game.david.carcelen.model.repository.mongo;

import dice.game.david.carcelen.model.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends MongoRepository<Game, String> {
    List<Game> findByIdPlayer(long idPlayer);
    void deleteByIdPlayer(long idPlayer);
}
