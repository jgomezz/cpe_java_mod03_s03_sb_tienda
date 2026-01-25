package pe.edu.tecsup.tienda.webs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.edu.tecsup.tienda.dtos.ProductoDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ProductoController {

    @GetMapping("/productos")
    public String index(Model model){

        log.info("call index");

        // Set string
        String mensaje = "Mensaje nuevo";
        model.addAttribute("msg",mensaje);

        // Set list
        List<ProductoDto> productos = new ArrayList<>();

        productos.add(ProductoDto.builder()
                .nombre("IPhone")
                .precio(3500.0)
                .build());

        productos.add(ProductoDto.builder()
                .nombre("Galaxy")
                .precio(2500.0)
                .build());

        productos.add(ProductoDto.builder()
                .nombre("Xiaomi")
                .precio(2000.0)
                .build());

        model.addAttribute("productos", productos);

        return "productos";  // PLANTILLA HTML

    }


}
