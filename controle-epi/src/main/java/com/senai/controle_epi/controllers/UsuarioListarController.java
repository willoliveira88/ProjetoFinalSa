package com.senai.controle_epi.controllers;

import com.senai.controle_epi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios/listar")
@RequiredArgsConstructor
public class UsuarioListarController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/listausuarios";
    }
}
