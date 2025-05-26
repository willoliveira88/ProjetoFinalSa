package com.senai.controle_epi.controllers;


import com.senai.controle_epi.dtos.UsuarioRequestDto;
import com.senai.controle_epi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/listausuarios";
    }

    @GetMapping("/cadastrar")
    public String formCadastrar(Model model) {
        model.addAttribute("usuario", new UsuarioRequestDto());
        return "usuarios/cadastrousuario";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("usuario") UsuarioRequestDto dto) {
        usuarioService.criarUsuario(dto);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
        return "usuarios/atualizarusuario";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("usuario") UsuarioRequestDto dto) {
        usuarioService.atualizarUsuario(id, dto);
        return "redirect:/usuarios";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
        return "redirect:/usuarios";
    }
}
