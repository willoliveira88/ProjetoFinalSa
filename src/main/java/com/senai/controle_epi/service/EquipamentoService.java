package com.senai.controle_epi.service;

import com.senai.controle_epi.dtos.EquipamentoRequestDto;
import com.senai.controle_epi.dtos.EquipamentoResponseDto;
import com.senai.controle_epi.models.EquipamentoModel;
import com.senai.controle_epi.repository.EquipamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipamentoService {


      @Autowired
     EquipamentoRepository equipamentoRepository;

    @Transactional
    public EquipamentoResponseDto criar(EquipamentoRequestDto dto) {
        EquipamentoModel equipamento = EquipamentoModel.builder()
                .descricao(dto.getDescricao())
                .tipo(dto.getTipo())
                .build();

        equipamentoRepository.save(equipamento);
        return mapToResponseDTO(equipamento);
    }

    public List<EquipamentoResponseDto> listarTodos() {
        return equipamentoRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public EquipamentoResponseDto buscarPorId(Long id) {
        EquipamentoModel equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));
        return mapToResponseDTO(equipamento);
    }

    @Transactional
    public EquipamentoResponseDto atualizar(Long id, EquipamentoRequestDto dto) {
        EquipamentoModel equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));

        equipamento.setDescricao(dto.getDescricao());
        equipamento.setTipo(dto.getTipo());

        equipamentoRepository.save(equipamento);
        return mapToResponseDTO(equipamento);
    }

    private EquipamentoResponseDto mapToResponseDTO(EquipamentoModel equipamento) {
        return EquipamentoResponseDto.builder()
                .id(equipamento.getId())
                .descricao(equipamento.getDescricao())
                .tipo(equipamento.getTipo())
                .build();
    }
}
