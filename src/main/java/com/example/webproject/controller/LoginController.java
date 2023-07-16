package com.example.webproject.controller;

import com.example.webproject.dao.UserDao;
import com.example.webproject.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserDao userDao;


    public String login(Model model) {
        return "login";
    }

    @PostMapping("/signin")
    public String loguinUid(@RequestParam("exampleCorreo") String email,
                            @RequestParam("inputPassword1") String password){

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail(email);
        userLoginDto.setPassword(password);
        userLoginDto.setReturnSecureToken(true);
        try {
            String loginUuid = userDao.login(userLoginDto);
            System.out.println("uuid: " + loginUuid);
            return "redirect:/home";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "redirect:/login";
        }

    }
}
