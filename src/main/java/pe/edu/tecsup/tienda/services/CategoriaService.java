package pe.edu.tecsup.tienda.services;


import pe.edu.tecsup.tienda.dtos.CategoriaDto;

import java.util.List;

public interface CategoriaService {

    List<CategoriaDto> findAll();

    CategoriaDto findById(Long id) throws Exception;

}
