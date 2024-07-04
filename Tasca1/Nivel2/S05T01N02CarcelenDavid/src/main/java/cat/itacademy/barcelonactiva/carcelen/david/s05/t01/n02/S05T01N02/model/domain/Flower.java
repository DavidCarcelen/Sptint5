package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String country;

    public Flower(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
