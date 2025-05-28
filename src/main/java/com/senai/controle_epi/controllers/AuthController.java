package com.senai.controle_epi.controllers;

import com.senai.controle_epi.dtos.*;
import com.senai.controle_epi.service.UsuarioService;
import com.senai.controle_epi.Sessao.ControleSessao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    UsuarioService service;

    @GetMapping("/login")
    public String obterLogin(Model model){

        LoginDto loginDto = new LoginDto();
        model.addAttribute("loginDto",loginDto);

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto login, HttpServletRequest request){

        UsuarioSessaoDto usuarioSessao =  service.logar(login);

        if (usuarioSessao.getId() != 0L){

            ControleSessao.registrar(request, usuarioSessao);

            return "redirect:/home";
        }

        return "redirect:/login?erro";

    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){

        ControleSessao.encerrar(request);
        return "redirect:/login";

    }

}
