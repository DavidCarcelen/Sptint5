package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.services.impl;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.exceptions.BranchNotFoundException;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.domain.Branch;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.repository.BranchRepo;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepo branchRepo;
    @Override
    public void addBranch(BranchDTO branchDTO) {
        Branch branch = new Branch(branchDTO.getName(),branchDTO.getCountry());
        branchRepo.save(branch);
    }

    /*@Override
    public void updateBranch(BranchDTO branchDTO) {
        if (!branchRepo.existsById(branchDTO.getId())) {
            throw new BranchNotFoundException("No branch found with id: " + (branchDTO.getId()));
        }
        Branch branch = new Branch(branchDTO.getName(),branchDTO.getCountry());
        branchRepo.save((Branch) branchDTO);
    }*/
    @Override
    public void updateBranch(BranchDTO branchDTO) {

        Optional<Branch> optionalBranch = branchRepo.findById(branchDTO.getId());
        if (optionalBranch.isPresent()) {
            Branch existingBranch = optionalBranch.get();
            existingBranch.setName(branchDTO.getName());
            existingBranch.setCountry(branchDTO.getCountry());
            branchRepo.save(existingBranch);
        } else {
            throw new RuntimeException("No se encontró la sucursal con ID: " + branchDTO.getId());
        }
    }

    @Override
    public void deleteBranch(int id) {
        if (!branchRepo.existsById(id)) {
            throw new BranchNotFoundException("No branch found with id: " + (id));
        }
        branchRepo.deleteById(id);
    }

    @Override
    public BranchDTO getOneBranch(int id) {
        Branch branch = branchRepo.findById(id).orElseThrow(()-> new BranchNotFoundException("No branch found with id: " + (id)));
        BranchDTO branchDTO = new BranchDTO(branch.getName(),branch.getCountry());
        branchDTO.setId(branch.getIdBranch());
        return branchDTO;
    }

    @Override
    public List<BranchDTO> getAllBranches() {

        return branchRepo.findAll().stream()
                .map(branch -> {
                    BranchDTO dto = new BranchDTO(
                            branch.getName(),
                            branch.getCountry()
                    );
                    dto.setId(branch.getIdBranch());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
