package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.repository;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepo extends JpaRepository<Branch,Integer> {
}
