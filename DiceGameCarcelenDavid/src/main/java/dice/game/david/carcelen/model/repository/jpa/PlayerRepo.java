package dice.game.david.carcelen.model.repository.jpa;

import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
    Optional<Player> findByName (String name);
    Optional<Player> findByEmail(String email);

}
