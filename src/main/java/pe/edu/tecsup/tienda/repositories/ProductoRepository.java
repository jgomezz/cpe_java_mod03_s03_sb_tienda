package pe.edu.tecsup.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.tienda.entities.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // findBy + NombreDelAtributo
    List<Producto> findByNombre(String nombre) throws Exception;

}
