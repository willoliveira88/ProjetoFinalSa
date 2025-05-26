package com.senai.controle_epi.service;

import com.senai.controle_epi.dtos.EmprestimoRequestDto;
import com.senai.controle_epi.dtos.EmprestimoResponseDto;
import com.senai.controle_epi.models.*;
import com.senai.controle_epi.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    EmprestimoRepository emprestimoRepository;

    @Autowired
     ColaboradorRepository colaboradorRepository;

    @Autowired
     EquipamentoRepository equipamentoRepository;

    @Transactional
    public EmprestimoResponseDto registrar(EmprestimoRequestDto dto) {
        ColaboradorModel colaborador = colaboradorRepository.findById(dto.getColaboradorId())
                .orElseThrow(() -> new IllegalArgumentException("Colaborador não encontrado"));
        EquipamentoModel equipamento = equipamentoRepository.findById(dto.getEquipamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));

        boolean emprestimoAberto = emprestimoRepository.findByColaboradorIdAndDevolucaoIsNull(colaborador.getId())
                .stream()
                .anyMatch(e -> e.getEquipamento().getId().equals(equipamento.getId()));

        if (emprestimoAberto) {
            throw new IllegalArgumentException("Este equipamento já está emprestado para o colaborador.");
        }

        EmprestimoModel emprestimo = EmprestimoModel.builder()
                .colaborador(colaborador)
                .equipamento(equipamento)
                .data(LocalDate.now())
                .observacao(dto.getObservacao())
                .build();

        emprestimoRepository.save(emprestimo);
        return mapToResponseDTO(emprestimo);
    }

    public List<EmprestimoResponseDto> listarTodos() {
        return emprestimoRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmprestimoResponseDto devolver(Long id, String observacao) {
        EmprestimoModel emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));

        emprestimo.setDevolucao(LocalDate.now());
        emprestimo.setObservacao(observacao);

        emprestimoRepository.save(emprestimo);
        return mapToResponseDTO(emprestimo);
    }

    public EmprestimoResponseDto mapToResponseDTO(EmprestimoModel emprestimo) {
        return EmprestimoResponseDto.builder()
                .id(emprestimo.getId())
                .colaboradorNome(emprestimo.getColaborador().getNome())
                .equipamentoDescricao(emprestimo.getEquipamento().getDescricao())
                .data(emprestimo.getData())
                .devolucao(emprestimo.getDevolucao())
                .observacao(emprestimo.getObservacao())
                .build();
    }

    public EmprestimoModel buscarPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));
    }
}
