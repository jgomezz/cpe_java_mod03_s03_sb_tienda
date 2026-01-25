package pe.edu.tecsup.tienda.webs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.tecsup.tienda.dtos.CategoriaDto;
import pe.edu.tecsup.tienda.dtos.ProductoDto;
import pe.edu.tecsup.tienda.services.CategoriaService;
import pe.edu.tecsup.tienda.services.ProductoService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 *  GET  http://localhost:8080/productos --> obtenga todos los productos
 *
 *
 */
@Slf4j
@Controller
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @GetMapping()
    public String index(Model model) throws Exception{

        log.info("call index");

        // Set list
        List<ProductoDto> productos
                = this.productoService.findAll();

        model.addAttribute("productos", productos);

        return "productos/index";  // PLANTILLA HTML

    }

    @GetMapping("/create")
    public String create(Model model) throws Exception {
        log.info("call create()");

        List<CategoriaDto> categorias = this.categoriaService.findAll();

        model.addAttribute("categorias", categorias);

        CategoriaDto newCategoriaDto = CategoriaDto.builder().build();
        ProductoDto newProductoDto = ProductoDto.builder().build();
        newProductoDto.setCategoria(newCategoriaDto);

        log.info(newProductoDto.toString());

        model.addAttribute("producto", newProductoDto);

        return "productos/create";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute("producto") ProductoDto producto,
                        Errors errors,
                        @RequestParam("file") MultipartFile file,
                        RedirectAttributes redirectAttrs) throws Exception {

        log.info("call store(producto: " + producto + ")");

        /*
        if(file != null && !file.isEmpty()) {

            String filename = System.currentTimeMillis() +
                              file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            producto.setImagen_nombre(filename);

            if(Files.notExists(Paths.get(STORAGEPATH))){
                Files.createDirectories(Paths.get(STORAGEPATH));
            }

            Files.copy(file.getInputStream(),
                    Paths.get(STORAGEPATH).resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        } */


        producto.setCreado(new Date()); // Fecha de creacion
        producto.setEstado(1);  // Activo

        this.productoService.save(producto);

        return "redirect:/productos";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes
            redirectAttrs) throws Exception {

        log.info("edit delete(id: " + id + ")");

        this.productoService.deleteById(id);

        redirectAttrs.addFlashAttribute("message", "Registro eliminado correctamente");

        return "redirect:/productos";
    }

}
