package pe.edu.tecsup.tienda.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.tecsup.tienda.dtos.CategoriaDto;
import pe.edu.tecsup.tienda.dtos.ProductoDto;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CategoriaServiceTest {

    @Autowired
    private CategoriaService categoriaService;

    @Test
    void findAll() {

        List<CategoriaDto> categorias = this.categoriaService.findAll();

        for (CategoriaDto categoriaDto : categorias) {
            log.info(categoriaDto.toString());
        }

        // Validaciones
        assertNotNull(categorias);  // que la lista no sea nula
        assertFalse(categorias.isEmpty()); // que la lista no esté vacía
        //assertEquals(3, categorias.size());

    }

    @Test
    void findById() {

        Long ID_PROD_SEARCH = 1L;
        String EXPECTED_NOMBRE = "Procesadores";


        try {
            var categoriaDto = this.categoriaService.findById(ID_PROD_SEARCH);
            //log.info(categoriaDto.toString());

            for(ProductoDto dto : categoriaDto.getProductos()) {
                log.info(dto.toString());
            }

            // Validaciones
            assertNotNull(categoriaDto);  // que el producto no sea nulo
            assertEquals(EXPECTED_NOMBRE, categoriaDto.getNombre());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}