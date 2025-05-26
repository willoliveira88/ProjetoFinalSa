package com.senai.controle_epi.dtos;



import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoDto {

    @NotNull(message = "Colaborador é obrigatório")
    private Long colaboradorId;

    @NotNull(message = "Equipamento é obrigatório")
    private Long equipamentoId;

    private String observacao;
}
