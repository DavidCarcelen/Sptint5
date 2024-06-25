package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.services.impl;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.exceptions.SucursalNotFoundException;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.repository.SucursalRepo;
import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SucursalServiceImpl implements SucursalService {
    @Autowired
    private SucursalRepo sucursalRepo;
    @Override
    public void addSucursal(Sucursal sucursal) {
        sucursalRepo.save(sucursal);

    }

    @Override
    public void updateSucursal(Sucursal sucursal) {
        if (!sucursalRepo.existsById(sucursal.getIdSucursal())) {
            throw new SucursalNotFoundException("No sucursal found with id: " + (sucursal.getIdSucursal()));
        }
        sucursalRepo.save(sucursal);
    }

    @Override
    public void deleteSucursal(int id) {
        if (!sucursalRepo.existsById(id)) {
            throw new SucursalNotFoundException("No sucursal found with id: " + (id));
        }
        sucursalRepo.deleteById(id);
    }

    @Override
    public Sucursal getOneSucursal(int id) {
        return sucursalRepo.findById(id).orElseThrow(()-> new SucursalNotFoundException("No sucursal found with id: " + (id)));
    }

    @Override
    public List<Sucursal> getAllSucursal() {
        return sucursalRepo.findAll();
    }

}
