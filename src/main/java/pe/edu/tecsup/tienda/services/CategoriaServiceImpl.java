package pe.edu.tecsup.tienda.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.tienda.dtos.CategoriaDto;
import pe.edu.tecsup.tienda.mapper.CategoriaMapper;
import pe.edu.tecsup.tienda.repositories.CategoriaRepository;


import java.util.List;

@Slf4j
@Service
@AllArgsConstructor // Genera el constructor con todos los atributos
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;

    @Override
    public List<CategoriaDto> findAll() {

        log.info("findAll categorias");

        // Usando el CategoriaMapper
        return repository.findAll().stream()
                .map(CategoriaMapper::toDto)
                .toList();

    }

    @Override
    public CategoriaDto findById(Long id) throws Exception {
        log.info("call findById()");
        //this.repository.findById(id) return wrapper Optional<Producto>
        return CategoriaMapper.toDto(this.repository.findById(id).get());    }
}
