package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.controllers;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.services.impl.SucursalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {
    @Autowired
    private SucursalServiceImpl sucursalService;

    @GetMapping("/")
    public String index (){
        return "home";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSucursal(@RequestBody Sucursal sucursal) {
        sucursalService.addSucursal(sucursal);
        return ResponseEntity.ok("Sucursal added");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateSucursal(@RequestBody Sucursal sucursal){
        sucursalService.updateSucursal(sucursal);
        return ResponseEntity.ok("Sucursal updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSucursal(@PathVariable int id){
        sucursalService.deleteSucursal(id);
        return ResponseEntity.ok("Sucursal " + id + " deleted");
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Sucursal> getOneSucursal(@PathVariable int id){
        Sucursal sucursal = sucursalService.getOneSucursal(id);
        return ResponseEntity.ok().body(sucursal);
    }

    @GetMapping("/getAll/")
    public ResponseEntity<List<Sucursal>> getAll(){
        List <Sucursal> list = sucursalService.getAllSucursal();
        return ResponseEntity.ok(list);
    }
}
