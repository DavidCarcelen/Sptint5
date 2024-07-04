package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class FlowerDTO implements Serializable {
    private int id;
    private String name;
    private String country;
    private String type;
    private static final List<String> euCountries = new ArrayList<>(Arrays.asList("portugal","spain","france",
            "austria","germany","italy","greece", "belgium", "bulgaria", "croatia", "republic of cyprus",
            "czech republic", "denmark", "estonia", "finland", "hungary", "ireland", "latvia", "lithuania",
            "luxembourg", "malta", "netherlands", "poland", "romania", "slovakia", "slovenia", "sweden"));

    public FlowerDTO(String name, String country) {
        this.name = name;
        this.country = country;
        this.type = country != null && euCountries.contains(country.toLowerCase()) ? "EU" : "NOT EU";
    }
}
