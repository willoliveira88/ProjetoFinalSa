package com.senai.controle_epi.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoRequestDto {
    @NotNull(message = "Colaborador é obrigatório")
    private Long colaboradorId;

    @NotNull(message = "Equipamento é obrigatório")
    private Long equipamentoId;

    private String observacao;
}

