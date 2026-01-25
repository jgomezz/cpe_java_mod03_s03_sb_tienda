package pe.edu.tecsup.tienda.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.tienda.dtos.ProductoDto;
import pe.edu.tecsup.tienda.mapper.ProductoMapper;
import pe.edu.tecsup.tienda.repositories.ProductoRepository;


import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository repository;

    /**
     * Método para listar todos los productos
     * @return Lista de productos
     * @throws Exception
     */
    @Override
    public List<ProductoDto> findAll() throws Exception {
        log.info("findAll productos");
        return this.repository.findAll().stream()
                .map(ProductoMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductoDto> findByName(String nombre) throws Exception {
        log.info("call findByName()");
        return this.repository.findByNombre(nombre).stream()
                .map(ProductoMapper::toDto)
                .toList();
    }

    @Override
    public ProductoDto findById(Long id) throws Exception {
        log.info("call findById()");
        // this.repository.findById(id) return wrapper Optional<Producto>
        return ProductoMapper.toDto(this.repository.findById(id).get());
    }

    @Override
    public void save(ProductoDto productoDto) throws Exception {
        log.info("call save()");
        this.repository.save(ProductoMapper.toEntity(productoDto));
    }

    @Override
    public void update(ProductoDto productoDto) throws Exception {
        log.info("call update()");
        this.repository.save(ProductoMapper.toEntity(productoDto));
    }

    @Override
    public void deleteById(Long id) throws Exception {
        log.info("call deleteById()");
        this.repository.deleteById(id);
    }
}
