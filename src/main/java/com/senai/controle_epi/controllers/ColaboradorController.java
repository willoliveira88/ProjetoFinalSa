package com.senai.controle_epi.controllers;


import com.senai.controle_epi.dtos.ColaboradorRequestDto;
import com.senai.controle_epi.service.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
     ColaboradorService colaboradorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("colaboradores", colaboradorService.listarTodos());
        return "colaboradores/listacolaboradores";
    }

    @GetMapping("/cadastrar")
    public String formCadastrar(Model model) {
        model.addAttribute("colaborador", new ColaboradorRequestDto());
        return "colaboradores/cadastrocolaborador";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("colaborador") ColaboradorRequestDto dto) {
        colaboradorService.criar(dto);
        return "redirect:/colaboradores";
    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        model.addAttribute("colaborador", colaboradorService.buscarPorId(id));
        return "colaboradores/atualizarcolaborador";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("colaborador") ColaboradorRequestDto dto) {
        colaboradorService.atualizar(id, dto);
        return "redirect:/colaboradores";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        colaboradorService.excluir(id);
        return "redirect:/colaboradores";
    }
}
