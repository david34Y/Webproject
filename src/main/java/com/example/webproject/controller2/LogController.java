package com.example.webproject.controller2;


import com.example.webproject.entity.Usuario;
import com.example.webproject.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("log")
public class LogController {
    @Autowired
    UsuarioRepository usuarioRepository;

    double contador = 0.0;
    @GetMapping("/loginn")
    public String login(Model model) {
        model.addAttribute("contador",contador);
        return "login";
    }



}
