package pe.edu.tecsup.tienda.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.tecsup.tienda.dtos.ProductoDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Test
    void findAll() {

        try {
            var productos = this.productoService.findAll();

            for (var producto : productos) {
                log.info(producto.toString());
            }

            // Validaciones
            assertNotNull(productos);  // que la lista no sea nula
            assertFalse(productos.isEmpty()); // que la lista no esté vacía
            //assertEquals(5, productos.size());

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    void findById() {

        Long ID_PROD_SEARCH = 1L;
        String EXPECTED_NOMBRE = "Intel Core I7";


        try {
            var producto = this.productoService.findById(ID_PROD_SEARCH);
            log.info(producto.toString());
            //log.info(producto.getCategoriaDto().toString());


            // Validaciones
            assertNotNull(producto);  // que el producto no sea nulo
            assertEquals(EXPECTED_NOMBRE, producto.getNombre());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void findByName() {
        // Datos de entrada
        String NOMBRE_SEARCH = "Kingstone";
        try {
            var productos =
                    this.productoService.findByName(NOMBRE_SEARCH);

            for (var producto : productos) {
                log.info(producto.toString());
            }

            // Validaciones
            assertNotNull(productos);  // que la lista no sea nula
            assertFalse(productos.isEmpty()); // que la lista no esté vacía
            //assertEquals(2, productos.size());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void save() {
        try {

            long totalAntes
                    = this.productoService.findAll().stream().count();
            log.info("Total antes de insertar: {}", totalAntes);

            var categoriaDto = this.categoriaService.findById(1L);

            var productoDto = ProductoDto.builder()
                    .categoria(categoriaDto) // Categoria de procesadores
                    .nombre("GTX-5070")
                    .descripcion("GPU para gaming de alta gama")
                    .precio(1500.00)
                    .stock(10)
                    .estado(1)  // Estado ACTIVO
                    .build();

            this.productoService.save(productoDto);
            long totalDespues
                    = this.productoService.findAll().stream().count();

            log.info("Total después de insertar: {}", totalDespues);

            assertEquals(1, totalDespues - totalAntes);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    @Test
    void update() {
        // Datos de entrada
        Long ID_PROD_UPDATE = 12L;   // Asegúrate de que este ID exista en la base de datos
        String NUEVO_NOMBRE = "iPhone 16 Pro Max";

        try {
            // Busca el producto que desea modificar
            ProductoDto productoDto = this.productoService.findById(ID_PROD_UPDATE);
            productoDto.setNombre(NUEVO_NOMBRE);

            // Actualizar el nombre del producto
            this.productoService.update(productoDto);

            // Recuperar el producto actualizado
            var productoActualizado =
                    this.productoService.findById(ID_PROD_UPDATE);

            log.info(productoActualizado.toString());

            // Validaciones
            assertNotNull(productoActualizado);  // que el producto no sea nulo
            assertEquals(NUEVO_NOMBRE, productoActualizado.getNombre());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void deleteById() {

        try {

            List<ProductoDto> productos = this.productoService.findAll();
            int totalAntes = productos.size();

            if (productos.isEmpty())
                return; // test pass

            ProductoDto ultimoProducto =
                    productos.get(productos.size() - 1);

            productoService.deleteById(ultimoProducto.getId());

            productos = productoService.findAll();
            int totalDespues = productos.size();

            assertEquals(1, totalAntes - totalDespues);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}