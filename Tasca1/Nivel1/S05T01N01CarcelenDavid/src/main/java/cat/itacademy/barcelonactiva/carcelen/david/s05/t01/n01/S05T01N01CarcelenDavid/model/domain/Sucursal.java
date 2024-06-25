package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.domain;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Sucursal {
    int idSucursal;
    String name;
    String country;

    public Sucursal(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
