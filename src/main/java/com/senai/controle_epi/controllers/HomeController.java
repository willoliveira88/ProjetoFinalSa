package com.senai.controle_epi.controllers;

import com.senai.controle_epi.dtos.UsuarioSessaoDto;
import com.senai.controle_epi.Sessao.ControleSessao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String obterHome(Model model, HttpServletRequest request){

        UsuarioSessaoDto usuarioSessao = ControleSessao.obter(request);

        if (usuarioSessao.getId() == 0){
            //--Não esta logado! voltar para o login
            return "redirect:/login";
        }

        model.addAttribute("nomeUsuario",usuarioSessao.getNome());
        return "home";
    }
}