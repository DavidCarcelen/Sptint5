package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idBranch;
    @Column(name = "branchName")
    String name;
    @Column(name = "branchCountry")
    String country;

    public Branch(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
