package com.senai.controle_epi.controllers;

import com.senai.controle_epi.dtos.UsuarioRequestDto;
import com.senai.controle_epi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos os usuários
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/listausuarios";
    }

    // Formulário para cadastrar novo usuário
    @GetMapping("/cadastrar")
    public String formCadastrar(Model model) {
        model.addAttribute("usuario", new UsuarioRequestDto());
        return "usuarios/cadastrousuario";
    }

    // Salvar novo usuário
    @PostMapping("/cadastrar")
    public String salvar(@Valid @ModelAttribute("usuario") UsuarioRequestDto dto) {
        usuarioService.criarUsuario(dto);
        return "redirect:/usuarios";
    }

    // Formulário para editar usuário existente
    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
        return "usuarios/atualizarusuario";
    }

    // Atualizar usuário
    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("usuario") UsuarioRequestDto dto) {
        usuarioService.atualizarUsuario(id, dto);
        return "redirect:/usuarios";
    }

    // Excluir usuário
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
        return "redirect:/usuarios";
    }
}
