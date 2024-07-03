package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.controllers;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.services.impl.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/branches")
public class BranchController {
    @Autowired
    private BranchServiceImpl branchService;

    @GetMapping("/")
    public String index (){
        return "home";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBranch(@RequestBody BranchDTO branchDTO) {
        branchService.addBranch(branchDTO);
        return ResponseEntity.ok("Branch added");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBranch(@RequestBody BranchDTO branchDTO){
        branchService.updateBranch(branchDTO);
        return ResponseEntity.ok("Branch updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBranch(@PathVariable int id){
        branchService.deleteBranch(id);
        return ResponseEntity.ok("Branch " + id + " deleted");
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<BranchDTO> getOneBranch(@PathVariable int id){
        BranchDTO branchDTO = branchService.getOneBranch(id);
        return ResponseEntity.ok().body(branchDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BranchDTO>> getAll(){
        List <BranchDTO> list = branchService.getAllBranches();
        return ResponseEntity.ok(list);
    }
}
