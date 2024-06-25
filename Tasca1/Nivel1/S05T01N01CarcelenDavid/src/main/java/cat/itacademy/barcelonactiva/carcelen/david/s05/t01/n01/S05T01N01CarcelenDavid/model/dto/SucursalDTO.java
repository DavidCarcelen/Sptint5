package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Data
public class SucursalDTO implements Serializable {
    private String sucursalType;
    private final List<String> euCountries = new ArrayList<>(Arrays.asList("Portugal","Spain","France","Austria","Germany","Italy","Greece", "Belgium", "Bulgaria", "Croatia", "Republic of Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "Hungary", "Ireland", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Romania", "Slovakia", "Slovenia", "Sweden"));

}
