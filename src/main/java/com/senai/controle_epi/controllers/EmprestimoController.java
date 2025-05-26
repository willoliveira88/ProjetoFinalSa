package com.senai.controle_epi.controllers;

import com.senai.controle_epi.dtos.EmprestimoRequestDto;
import com.senai.controle_epi.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {


    @Autowired
     EmprestimoService emprestimoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoService.listarTodos());
        return "emprestimos/listaemprestimos";
    }

    @GetMapping("/cadastrar")
    public String formCadastrar(Model model) {
        model.addAttribute("emprestimo", new EmprestimoRequestDto());
        return "emprestimos/cadastroemprestimo";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("emprestimo") EmprestimoRequestDto dto) {
        emprestimoService.registrar(dto);
        return "redirect:/emprestimos";
    }

    @GetMapping("/devolver/{id}")
    public String formDevolucao(@PathVariable Long id, Model model) {
        model.addAttribute("emprestimo", emprestimoService.buscarPorId(id));
        return "emprestimos/devolucaoemprestimo";
    }

    @PostMapping("/devolver/{id}")
    public String devolver(@PathVariable Long id, @RequestParam("observacao") String observacao) {
        emprestimoService.devolver(id, observacao);
        return "redirect:/emprestimos";
    }
}
