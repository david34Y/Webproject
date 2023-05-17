package com.example.webproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitialController {

    @GetMapping("/productos")
    public String productos(){
        return "/manager/productos";
    }

    @GetMapping("/productos/nuevo")
    public String nuevoproducto(){
        return "/manager/productosnuevo";
    }

    @GetMapping("/productos/editar")
    public String editarproducto(){
        return "/manager/productoseditar";
    }

    @GetMapping("/clientes")
    public String clientes(){
        return "/manager/clientes";
    }

    @GetMapping("/reportes")
    public String reportes(){
        return "/manager/reportes";
    }

    @GetMapping("/compras")
    public String compras(){
        return "/manager/compras";
    }

    @GetMapping("/managers")
    public String managers(){
        return "/admin/managers";
    }

    @GetMapping("/about")
    public String acercade(){
        return "about";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

    @GetMapping("/cart")
    public String carrito(){
        return "cart";
    }

    @GetMapping("/checkout")
    public String compra(){
        return "checkout";
    }

    @GetMapping("/contact")
    public String contacto(){
        return "contact";
    }

    @GetMapping("/index")
    public String indice(){
        return "index";
    }

    @GetMapping("/portfolio")
    public String portafolio(){
        return "portfolio";
    }

    @GetMapping("/shop")
    public String tienda(){
        return "shop";
    }

    @GetMapping("/shop-details")
    public String detallestienda(){
        return "shop-details";
    }

    @GetMapping("/single-portfolio")
    public String portafoliosolo(){
        return "single-portfolio";
    }

    @GetMapping("/single-post")
    public String postunico(){
        return "single-post";
    }


}
