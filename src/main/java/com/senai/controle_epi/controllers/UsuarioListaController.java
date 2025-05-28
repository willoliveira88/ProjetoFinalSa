package com.senai.controle_epi.controllers;

import com.senai.controle_epi.Sessao.ControleSessao;
import com.senai.controle_epi.dtos.ListaUsuariosDto;
import com.senai.controle_epi.dtos.UsuarioSessaoDto;
import com.senai.controle_epi.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
//@RequestMapping("/usuario")
public class UsuarioListaController {

    @Autowired
    UsuarioService service;

    @GetMapping("/usuariolista")
    public String obterUsuarioLista(Model model, HttpServletRequest request){

        UsuarioSessaoDto usuarioSessao = ControleSessao.obter(request);

        if (usuarioSessao.getId() == 0){
            //--Não esta logado! voltar para o login
            return "redirect:/login";
        }

        List<ListaUsuariosDto> listaUsuariosDto = service.listarUsuarios();
        model.addAttribute("listaUsuariosDto",listaUsuariosDto);

        return "usuariolista";
    }

}
