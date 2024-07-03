package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.services.impl;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.exceptions.BranchNotFoundException;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.domain.Branch;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.repository.BranchRepo;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepo branchRepo;
    @Override
    public void addBranch(BranchDTO branchDTO) {
        Branch sucursal = new Branch(branchDTO.getName(),branchDTO.getCountry());
        branchRepo.save(sucursal);
    }

    @Override
    public void updateBranch(BranchDTO branchDTO) {
        Branch branch = new Branch(branchDTO.getName(),branchDTO.getCountry());
        if (!branchRepo.existsById(branch.getIdBranch())) {
            throw new BranchNotFoundException("No branch found with id: " + (branch.getIdBranch()));
        }
        branchRepo.save(branch);
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
        return new BranchDTO(branch.getName(),branch.getCountry());
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
