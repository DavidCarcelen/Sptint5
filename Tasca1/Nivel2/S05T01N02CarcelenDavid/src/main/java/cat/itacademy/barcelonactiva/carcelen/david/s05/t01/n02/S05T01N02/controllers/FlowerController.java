package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.controllers;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.domain.Flower;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.services.FlowerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flowers")
public class FlowerController {

    @Autowired
    private FlowerService flowerService;
    @Operation(summary = "Add a list of flowers", description = "Adds a list of flower objects to the database")
    @PostMapping("/add")
    public ResponseEntity<String> addFlower(@RequestBody FlowerDTO flowerDTO) {
        flowerService.addFlower(flowerDTO);
        return ResponseEntity.ok("flower added");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateFlower(@RequestBody FlowerDTO flowerDTO) {
        flowerService.updateFlower(flowerDTO);
        return ResponseEntity.ok("flower updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFruit(@PathVariable int id) {
        flowerService.deleteFlower(id);
        return ResponseEntity.ok("Fruit deleted");
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<FlowerDTO> getFruitById(@PathVariable int id) {
        FlowerDTO flowerDTO = flowerService.getOneFlower(id);
        return ResponseEntity.ok().body(flowerDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FlowerDTO>> getAllFruits() {
        List<FlowerDTO> flowers = flowerService.getAllFlowers();
        return ResponseEntity.ok(flowers);
    }

}
