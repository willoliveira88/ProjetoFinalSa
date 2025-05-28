package com.senai.controle_epi.controllers;

import com.senai.controle_epi.Sessao.ControleSessao;
import com.senai.controle_epi.dtos.UsuarioAtualizarDto;
import com.senai.controle_epi.dtos.UsuarioSessaoDto;
import com.senai.controle_epi.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarioatualizar")
public class UsuarioAtualizarController {

    @Autowired
    UsuarioService service;

    @GetMapping("/{id}")
    public String obterUsuario(Model model, @PathVariable Long id, HttpServletRequest request){

        UsuarioSessaoDto usuarioSessao = ControleSessao.obter(request);

        if (usuarioSessao.getId() == 0){
            //--Não esta logado! voltar para o login
            return "redirect:/login";
        }

        UsuarioAtualizarDto usuarioCadastroDto = service.buscarUsuarioId(id);

        if (usuarioCadastroDto.getId() == 0){
            //--Não encontrou o usuário!
            return "redirect:/usuariolista";
        }

        model.addAttribute("usuarioAtualizarDto", usuarioCadastroDto);
        return "usuarioatualizar";

    }

}
