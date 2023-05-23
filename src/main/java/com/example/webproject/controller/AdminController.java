package com.example.webproject.controller;

import com.example.webproject.entity.Detallecompra;
import com.example.webproject.entity.Plantas;
import com.example.webproject.entity.Rol;
import com.example.webproject.entity.Usuario;
import com.example.webproject.repository.RolRepository;
import com.example.webproject.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @GetMapping(value={"/list","/",""})
    public String listarManagers(Model model) {
        model.addAttribute("managerList",usuarioRepository.findAllManagers());
        return  "admin/managers";
    }

    @GetMapping(value = "/new")
    public String nuevoManager(@ModelAttribute("usuario") Usuario usuario){
        return "admin/managersnewFrm";
    }

    @PostMapping(value = "/save")
    public String guardarManager(RedirectAttributes attr,
                                 @ModelAttribute("usuario")  Usuario usuario,
                                 BindingResult bindingResult,
                                 @RequestParam("idmanager") int id){
        Optional<Rol> opt = rolRepository.findById(id);
        
        if (bindingResult.hasErrors()){
            return "admin/managersnewFrm";
        }else{
            opt.ifPresent(usuario::setRol);
            attr.addFlashAttribute("msg", "Producto creado exitosamente");
            usuarioRepository.save(usuario);
            return "redirect:/admin/list";
        }
    }

    @GetMapping(value = "/edit")
    public String editarManager(@ModelAttribute("usuario") Usuario usuario,
                                 Model model,
                                 @RequestParam("id") int id){
        Optional<Usuario> opt = usuarioRepository.findById(id);

        if(opt.isPresent()){
            usuario = opt.get();
            model.addAttribute("usuario",usuario);

            return "admin/managersnewFrm";
        }else{

            return "redirect:/admin/list";
        }
    }

    @GetMapping(value = "/delete")
    public String eliminarManager(RedirectAttributes attr,
                                   @RequestParam("id") int id){
        usuarioRepository.deleteById(id);
        attr.addFlashAttribute("msg2", "Producto borrado exitosamente");
        return "redirect:/admin/list";
    }
}
