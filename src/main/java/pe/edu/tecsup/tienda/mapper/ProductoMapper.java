package pe.edu.tecsup.tienda.mapper;


import pe.edu.tecsup.tienda.dtos.ProductoDto;
import pe.edu.tecsup.tienda.entities.Producto;

public class ProductoMapper {

    public static ProductoDto toDto(Producto entity) {
        return ProductoDto.builder()
            .id(entity.getId())
            .nombre(entity.getNombre())
            .descripcion(entity.getDescripcion())
            .precio(entity.getPrecio())
            .stock(entity.getStock())
            .estado(entity.getEstado())
            .creado(entity.getCreado())
            .categoria(CategoriaMapper.toDto(entity.getCategoria()))
            .build();
    }

    public static Producto toEntity(ProductoDto dto) {

        Producto entity = new Producto();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        entity.setEstado(dto.getEstado());
        entity.setCreado(dto.getCreado());
        entity.setCategoria(CategoriaMapper.toEntity(dto.getCategoria()));

        return entity;
    }

}
