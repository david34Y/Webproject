package com.example.webproject.controller;

import com.example.webproject.entity.Compra;
import com.example.webproject.entity.Consulta;
import com.example.webproject.entity.Detallecompra;
import com.example.webproject.entity.Usuario;
import com.example.webproject.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.webproject.controller.PlantasController.contador;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    DetallecompraRepository detallecompraRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    @GetMapping("/registro")
    public String registro(@ModelAttribute("usuario") Usuario usuario, Model model){
        model.addAttribute("contador",contador);
        return "register";
    }

    @PostMapping("/guardarusuario")
    public String guardarUser(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult,
                              Model model, RedirectAttributes attr, @RequestParam("contrasenia2") String contrasenia2)  {
        //VALIDANDO SI EXISTE EL CLIENTE
        List<Usuario> clientesxcorreo = usuarioRepository.findUsuarioByCorreo(usuario.getCorreo());
        if (!clientesxcorreo.isEmpty()) {
            bindingResult.rejectValue("correo", "error.Usuario", "El correo ingresado ya se encuentra en la base de datos");
        }


        if (bindingResult.hasErrors() || !contrasenia2.equals(usuario.getPassword())) {

            if (!contrasenia2.equals(usuario.getPassword())) {
                model.addAttribute("msg", "Las contraseñas no coinciden");
            }
            model.addAttribute("contador",contador);

            return "register";
        } else {
            //
            System.out.println(rolRepository.findrol(2).get(0));
            usuario.setRol(rolRepository.findrol(2).get(0));

            attr.addFlashAttribute("msg", "usuario creado exitosamente");



            /* BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(cliente.getContrasenia());
            System.out.println(hashedPassword);*/
            usuario.setPassword(usuario.getPassword());

            //guardamos user
            usuarioRepository.save(usuario);


            /////----------------Envio Correo--------------------/////

            //sendHtmlMailREgistrado(cliente.getCorreo(), "Cliente registrado", cliente);


            /////-----------------------------------------  ------/////

            model.addAttribute("contador",contador);
            return "redirect:/shop";

        }
    }


    @GetMapping("/contact")
    public String contacto(@ModelAttribute("consultante") Consulta consultante, Model model){
        model.addAttribute("contador",contador);
        return "contact";
    }

    @GetMapping("/contact_cli")
    public String contacto_cliente(@ModelAttribute("consultante") Consulta consultante, Model model){
        model.addAttribute("contador",contador);
        return "user/contact";
    }


    @PostMapping("/guardarconsultante")
    public String guardarConsultante(@ModelAttribute("consultante") @Valid Consulta consultante, BindingResult bindingResult,
                                     Model model, RedirectAttributes attr)  {

        List<Usuario> clientesxcorreo = usuarioRepository.findUsuarioByCorreo(consultante.getCorreocontacto());
        if (!clientesxcorreo.isEmpty()) {
            Usuario usuario=clientesxcorreo.get(0);
            consultante.setUser(usuario);
        }


        if (bindingResult.hasErrors()) {

            return "contact";
        } else {
            //


            //guardamos user
            consultaRepository.save(consultante);


            /////----------------Envio Correo--------------------/////

            //sendHtmlMailREgistrado(cliente.getCorreo(), "Cliente registrado", cliente);


            /////-----------------------------------------  ------/////

            model.addAttribute("contador",contador);
            return "redirect:/contact";

        }
    }

    //------------------------PERFIL---------------------

    @GetMapping("/perfil_cli")
    public String perfil_cliente(Model model, Principal principal) {
        /*
        String usuarioCorreo = principal.getName(); // Obtiene el correo del usuario logueado
        Usuario usuario = usuarioRepository.findUsuarioByCorreo(usuarioCorreo);

         */
        //reemplaza el 4 con el id del usuario
        List<Compra> compras = compraRepository.findComprasByUsuarioId(4);

        List<List<Detallecompra>> detallesCompras = new ArrayList<>();
        for (Compra compra : compras) {
            List<Detallecompra> detallesCompra = detallecompraRepository.findByComprasID(compra.getIdcompra());
            detallesCompras.add(detallesCompra);
        }
        model.addAttribute("detallesCompras", detallesCompras);
        //model.addAttribute("usuario", usuario);

        return "user/perfil";
    }






}
