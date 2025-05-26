package com.senai.controle_epi.service;


import com.senai.controle_epi.dtos.ColaboradorRequestDto;
import com.senai.controle_epi.dtos.ColaboradorResponseDto;
import com.senai.controle_epi.models.ColaboradorModel;
import com.senai.controle_epi.repository.ColaboradorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColaboradorService {

    @Autowired
     ColaboradorRepository colaboradorRepository;

    @Transactional
    public ColaboradorResponseDto criar(ColaboradorRequestDto dto) {
        if (colaboradorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        int idade = Period.between(dto.getNascimento(), LocalDate.now()).getYears();
        if (idade >= 120 || idade < 0) {
            throw new IllegalArgumentException("Idade inválida");
        }

        ColaboradorModel colaborador = ColaboradorModel.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .funcao(dto.getFuncao())
                .nascimento(dto.getNascimento())
                .build();

        colaboradorRepository.save(colaborador);
        return mapToResponseDTO(colaborador);
    }

    public List<ColaboradorResponseDto> listarTodos() {
        return colaboradorRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public ColaboradorResponseDto buscarPorId(Long id) {
        ColaboradorModel colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Colaborador não encontrado"));
        return mapToResponseDTO(colaborador);
    }

    @Transactional
    public ColaboradorResponseDto atualizar(Long id, ColaboradorRequestDto dto) {
        ColaboradorModel colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Colaborador não encontrado"));

        int idade = Period.between(dto.getNascimento(), LocalDate.now()).getYears();
        if (idade >= 120 || idade < 0) {
            throw new IllegalArgumentException("Idade inválida");
        }

        colaborador.setNome(dto.getNome());
        colaborador.setEmail(dto.getEmail());
        colaborador.setFuncao(dto.getFuncao());
        colaborador.setNascimento(dto.getNascimento());

        colaboradorRepository.save(colaborador);
        return mapToResponseDTO(colaborador);
    }

    @Transactional
    public void excluir(Long id) {
        colaboradorRepository.deleteById(id);
    }

    private ColaboradorResponseDto mapToResponseDTO(ColaboradorModel colaborador) {
        return ColaboradorResponseDto.builder()
                .id(colaborador.getId())
                .nome(colaborador.getNome())
                .email(colaborador.getEmail())
                .funcao(colaborador.getFuncao())
                .nascimento(colaborador.getNascimento())
                .build();
    }
}
