package com.senai.controle_epi.controllers;

import com.senai.controle_epi.dtos.UsuarioRequestDto;
import com.senai.controle_epi.models.UsuarioModel;
import com.senai.controle_epi.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("usuario", new UsuarioRequestDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("usuario") UsuarioRequestDto dto, HttpSession session, Model model) {
        UsuarioModel usuario = usuarioRepository.findByEmail(dto.getEmail())
                .filter(u -> u.getSenha().equals(dto.getSenha()))
                .orElse(null);

        if (usuario == null) {
            model.addAttribute("erro", "E-mail ou senha inválidos");
            return "login";
        }

        session.setAttribute("usuarioLogado", usuario);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
