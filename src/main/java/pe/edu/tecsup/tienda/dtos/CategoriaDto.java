package pe.edu.tecsup.tienda.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoriaDto {

    private Long id;
    private String nombre;
    private Integer orden;
    private List<ProductoDto>  productos;
}
