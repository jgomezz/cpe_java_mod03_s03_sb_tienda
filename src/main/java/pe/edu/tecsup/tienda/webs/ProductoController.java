package pe.edu.tecsup.tienda.webs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.tecsup.tienda.dtos.ProductoDto;
import pe.edu.tecsup.tienda.services.ProductoService;

import java.util.ArrayList;
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

    @GetMapping()
    public String index(Model model) throws Exception{

        log.info("call index");

        // Set list
        List<ProductoDto> productos
                = this.productoService.findAll();

        model.addAttribute("productos", productos);

        return "productos/index";  // PLANTILLA HTML

    }


}
