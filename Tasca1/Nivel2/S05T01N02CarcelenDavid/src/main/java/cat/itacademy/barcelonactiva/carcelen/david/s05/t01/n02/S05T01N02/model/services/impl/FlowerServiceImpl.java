package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.services.impl;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.exceptions.FlowerNotFoundException;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.domain.Flower;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.repository.FlowerRepo;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    private FlowerRepo flowerRepo;

    @Override
    public void addFlower(FlowerDTO flowerDTO) {
        Flower flower = new Flower(flowerDTO.getName(), flowerDTO.getCountry());
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
        if (!flowerRepo.existsById(id)) {
            throw new FlowerNotFoundException("No flower found with id: " + (id));
        }
        flowerRepo.deleteById(id);

    }

    @Override
    public FlowerDTO getOneFlower(int id) {
        Flower flower = flowerRepo.findById(id).orElseThrow(() -> new FlowerNotFoundException("No flower found with id: " + (id)));
        FlowerDTO flowerDTO = new FlowerDTO(flower.getName(), flower.getCountry());
        flowerDTO.setId(id);
        return flowerDTO;
    }

    @Override
    public List<FlowerDTO> getAllFlowers() {
        return flowerRepo.findAll().stream()
                .map(flower -> {
                    FlowerDTO flowerDTO = new FlowerDTO(flower.getName(), flower.getCountry());
                    flowerDTO.setId(flower.getId());
                    return flowerDTO;
                })
                .collect(Collectors.toList());
    }
}
