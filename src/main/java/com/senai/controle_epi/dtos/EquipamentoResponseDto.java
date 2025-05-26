package com.senai.controle_epi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipamentoResponseDto {
    private Long id;
    private String descricao;
    private String tipo;
}
