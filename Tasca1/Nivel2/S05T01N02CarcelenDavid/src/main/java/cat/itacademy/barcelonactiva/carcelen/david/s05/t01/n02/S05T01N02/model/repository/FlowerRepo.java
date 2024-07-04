package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.repository;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.domain.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepo extends JpaRepository<Flower, Integer> {
}
