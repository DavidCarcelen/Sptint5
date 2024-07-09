package dice.game.david.carcelen.model.dtos;


import java.util.Date;

public class PlayerDTO {
    private int id;
    private Date signDate;
    private String name;

    public PlayerDTO(String name) {
        this.name = name;
    }
}
