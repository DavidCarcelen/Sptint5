package dice.game.david.carcelen.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
}
