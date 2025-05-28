package com.senai.controle_epi.controllers;

import com.senai.controle_epi.dtos.RequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @GetMapping
    public String viewCadastro(Model model){

        RequestDto requestDto = new RequestDto();
        model.addAttribute("requestDto",requestDto);

        return "cadastro";
    }



}