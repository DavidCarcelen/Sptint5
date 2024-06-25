package cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.services;

import cat.itacademy.barcelonactiva.carcelen.david.s05.t01.n01.S05T01N01CarcelenDavid.model.domain.Sucursal;

import java.util.List;

public interface SucursalService {

    void addSucursal(Sucursal sucursal);
    void updateSucursal(Sucursal sucursal);
    void deleteSucursal(int id);
    Sucursal getOneSucursal(int id);
    List<Sucursal> getAllSucursal();

}
