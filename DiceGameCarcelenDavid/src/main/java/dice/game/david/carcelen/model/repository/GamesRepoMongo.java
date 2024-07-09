package dice.game.david.carcelen.model.repository;

import dice.game.david.carcelen.model.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GamesRepoMongo extends MongoRepository<Game, String> {
}
