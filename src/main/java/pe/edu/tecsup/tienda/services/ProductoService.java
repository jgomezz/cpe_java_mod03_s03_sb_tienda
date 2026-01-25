package pe.edu.tecsup.tienda.services;


import pe.edu.tecsup.tienda.dtos.ProductoDto;

import java.util.List;

public interface ProductoService {

    List<ProductoDto> findAll() throws Exception;
    List<ProductoDto> findByName(String nombre) throws Exception;
    ProductoDto findById(Long id) throws Exception;

    void save(ProductoDto productoDto) throws Exception;

    void update(ProductoDto productoDto) throws Exception;

    void deleteById(Long id) throws Exception;

}
