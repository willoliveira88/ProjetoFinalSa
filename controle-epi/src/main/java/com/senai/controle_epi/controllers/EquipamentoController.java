package com.senai.controle_epi.controllers;

import com.senai.controle_epi.dtos.EquipamentoRequestDto;
import com.senai.controle_epi.service.EquipamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("equipamentos", equipamentoService.listarTodos());
        return "equipamentos/listaequipamentos";
    }

    @GetMapping("/cadastrar")
    public String formCadastrar(Model model) {
        model.addAttribute("equipamento", new EquipamentoRequestDto());
        return "equipamentos/cadastroequipamento";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("equipamento") EquipamentoRequestDto dto) {
        equipamentoService.criar(dto);
        return "redirect:/equipamentos";
    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        model.addAttribute("equipamento", equipamentoService.buscarPorId(id));
        return "equipamentos/atualizarequipamento";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("equipamento") EquipamentoRequestDto dto) {
        equipamentoService.atualizar(id, dto);
        return "redirect:/equipamentos";
    }
}
