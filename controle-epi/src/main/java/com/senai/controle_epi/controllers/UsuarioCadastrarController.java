package com.senai.controle_epi.controllers;

import ch.qos.logback.core.model.Model;
import com.senai.controle_epi.dtos.UsuarioRequestDto;
import com.senai.controle_epi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios/cadastrar")
@RequiredArgsConstructor
public class UsuarioCadastrarController {
      @Autowired
      UsuarioService usuarioService;

    @GetMapping
    public String formCadastrar(Model model) {
        model.addAttribute( "usuario", new UsuarioRequestDto());
        return "usuarios/cadastrousuario";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("usuario") UsuarioRequestDto dto) {
        usuarioService.criarUsuario(dto);
        return "redirect:/usuarios/listar";
    }
}
