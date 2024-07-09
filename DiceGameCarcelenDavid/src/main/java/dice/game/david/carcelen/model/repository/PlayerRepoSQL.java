package dice.game.david.carcelen.model.repository;

import dice.game.david.carcelen.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepoSQL extends JpaRepository<Player, Long> {
}
