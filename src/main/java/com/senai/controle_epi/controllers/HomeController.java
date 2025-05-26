package com.senai.controle_epi.controllers;


import com.senai.controle_epi.models.UsuarioModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/auth/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuarioLogado");
        model.addAttribute("nome", usuario.getNome());
        return "home";
    }
}
