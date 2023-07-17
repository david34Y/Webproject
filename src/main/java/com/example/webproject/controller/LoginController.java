package com.example.webproject.controller;

import com.example.webproject.dao.UserDao;
import com.example.webproject.dto.UserLoginDto;
import com.example.webproject.entity.Usuario;
import com.example.webproject.repository.UsuarioRepository;
import com.google.api.Http;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.webproject.controller.PlantasController.contador;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserDao userDao;

    @GetMapping("")
    public String login(Model model) {
        model.addAttribute("contador","0");
        return "login";
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/signin")
    public String loginObtainID(@RequestParam("exampleCorreo") String email,
                                @RequestParam("inputPassword1") String password,
                                HttpSession session){

        try {
            Integer loginID = usuarioRepository.loginUsuario(email, password);
            Integer rolID = usuarioRepository.obtenerRolID(email, password);

            System.out.println("Login ID: " + loginID);
            Usuario usuario = usuarioRepository.findUsuarioById(loginID);
            System.out.println("Rol ID: "+rolID);
            session.setAttribute("user", usuario);
            if(rolID == 1){
                System.out.println("Manager");
                return "redirect:/manager/";
            } else if (rolID == 2) {
                System.out.println("Cliente");
                return "redirect:/cliente/index";
            } else{
                System.out.println("Admin");
                return "redirect:/admin/";
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "redirect:/login";
    }

    @GetMapping("/signout")
    public String logout(HttpSession session) {
        contador=0;
        session.invalidate(); // Invalida la sesión actual
        return "redirect:/user/login"; // Redirecciona al inicio de sesión
    }


}
