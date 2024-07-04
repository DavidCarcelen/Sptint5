package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.controllers;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.domain.Flower;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n02.S05T01N02.model.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flowerss")
public class FlowerController {

    @Autowired
    private FlowerService flowerService;

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
        fruitService.deleteFruit(id);
        return ResponseEntity.ok("Fruit deleted");
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable int id) {
        Fruit fruit = fruitService.getFruitById(id);
        return ResponseEntity.ok().body(fruit);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Fruit>> getAllFruits() {
        List<Fruit> fruits = fruitService.getAllFruits();
        return ResponseEntity.ok(fruits);
    }

}
