package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.services;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.dto.BranchDTO;

import java.util.List;

public interface BranchService {

    void addBranch(BranchDTO branchDTO);
    void updateBranch(BranchDTO branchDTO);
    void deleteBranch(int id);
    BranchDTO getOneBranch(int id);
    List<BranchDTO> getAllBranches();

}
