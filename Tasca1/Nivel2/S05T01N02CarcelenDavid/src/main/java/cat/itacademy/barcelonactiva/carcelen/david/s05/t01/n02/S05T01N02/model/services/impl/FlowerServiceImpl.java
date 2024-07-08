package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.services.impl;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.exceptions.FlowerNotFoundException;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.domain.Flower;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.repository.FlowerRepo;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    private FlowerRepo flowerRepo;

    @Override
    public void addFlower(FlowerDTO flowerDTO) {
        Flower flower = new Flower(flowerDTO.getName(),flowerDTO.getCountry());
        flowerRepo.save(flower);

    }

    @Override
    public void updateFlower(FlowerDTO flowerDTO) {
        Flower existingFlower = flowerRepo.findById(flowerDTO.getId())
                .orElseThrow(() -> new FlowerNotFoundException("No flower found with id: " + flowerDTO.getId()));
        existingFlower.setName(flowerDTO.getName());
        existingFlower.setCountry(flowerDTO.getCountry());
        flowerRepo.save(existingFlower);
    }

    @Override
    public void deleteFlower(int id) {

    }

    @Override
    public FlowerDTO getOneFlower(int id) {
        return null;
    }

    @Override
    public List<FlowerDTO> getAllFlowers() {
        return null;
    }
}
