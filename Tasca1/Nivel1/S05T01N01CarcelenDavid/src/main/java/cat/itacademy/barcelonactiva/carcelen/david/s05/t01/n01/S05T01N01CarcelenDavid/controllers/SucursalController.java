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

    @PostMapping("/add")
    public ResponseEntity<String> addSucursal(@RequestBody Sucursal sucursal) {
        sucursalService.addSucursal(sucursal);
        return ResponseEntity.ok("Sucursal added");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateSucursal(@RequestBody Sucursal sucursal){
        sucursalService.updateSucursal(sucursal);
        return ResponseEntity.ok("Sucursal updated");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteSucursal(@RequestBody Sucursal sucursal){
        sucursalService.deleteSucursal(sucursal.getIdSucursal());
        return ResponseEntity.ok("Sucursal with deleted");
    }

    @PostMapping("/getOne/{id}")
    public ResponseEntity<Sucursal> getOneSucursal(@PathVariable int id){
        Sucursal sucursal = sucursalService.getOneSucursal(id);
        return ResponseEntity.ok().body(sucursal);
    }

    @PostMapping("/getAll/")
    public ResponseEntity<List<Sucursal>> getAll(){
        List <Sucursal> list = sucursalService.getAllSucursal();
        return ResponseEntity.ok(list);
    }
    //http://localhost:9000/sucursal/add
    //
    //http://localhost:9000/sucursal/update
    //
    //http://localhost:9000/sucursal/delete/{id}
    //
    //http://localhost:9000/sucursal/getOne/{id}
    //
    //http://localhost:9000/sucursal/getAll
}
