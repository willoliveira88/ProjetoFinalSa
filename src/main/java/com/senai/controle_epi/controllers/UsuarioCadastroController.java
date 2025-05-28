package com.senai.controle_epi.controllers;

import com.senai.controle_epi.Sessao.ControleSessao;
import com.senai.controle_epi.dtos.RequestDto;
import com.senai.controle_epi.dtos.UsuarioSessaoDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioCadastroController {

    @GetMapping("/usuariocadastro")
    public String viewCadastroUsuario(Model model, HttpServletRequest request){

        UsuarioSessaoDto usuarioSessao = ControleSessao.obter(request);

        if (usuarioSessao.getId() == 0){
            //--Não esta logado! voltar para o login
            return "redirect:/login";
        }

        RequestDto usuarioDto = new RequestDto();
        model.addAttribute("usuarioDto", usuarioDto);

        return "usuariocadastro";
    }
}
