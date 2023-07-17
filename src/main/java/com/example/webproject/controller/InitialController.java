package com.example.webproject.controller;

import com.example.webproject.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.webproject.controller.PlantasController.contador;

@Controller
public class InitialController {

    @GetMapping("/productos")
    public String productos(){
        return "/manager/productos";
    }

    @GetMapping("/clientes")
    public String clientes(){
        return "/manager/clientes";
    }

    @GetMapping("/reportes")
    public String reportes(){
        return "compras";
    }

    @GetMapping("/compras")
    public String compras(){
        return "reportes1";
    }

    @GetMapping("/managers")
    public String managers(){
        return "/admin/managers";
    }

    //USUARIO

    @GetMapping("/about")
    public String acercade(Model model){
        model.addAttribute("contador",contador);
        return "about";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("contador",contador);
        return "home";
    }


    @GetMapping("/cart")
    public String carrito(){
        return "cart";
    }

    @GetMapping("/checkout")
    public String compra(){
        return "checkout";
    }

    @GetMapping("/index")
    public String indice(Model model){

        model.addAttribute("contador",contador);
        return "index";
    }
    @GetMapping("/portfolio")
    public String portafolio(){
        return "portfolio";
    }


    //--------------login controlller-----------------



/*
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("contador",contador);
        return "login";
    }
*/


    @GetMapping("/single-portfolio")
    public String portafoliosolo(Model model){
        model.addAttribute("contador",contador);
        return "single-portfolio";
    }

    //-----------------client controller------

    @GetMapping("/cart_cli")
    public String carrito_cliente(){
        return "user/cart";
    }











}
