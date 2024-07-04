package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.services;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.dto.FlowerDTO;
import java.util.List;

public interface FlowerService {

    void addFlower(FlowerDTO flowerDTO);
    void updateFlower(FlowerDTO flowerDTO);
    void deleteFlower(int id);
    FlowerDTO getOneFlower(int id);
    List<FlowerDTO> getAllFlowers();

}
