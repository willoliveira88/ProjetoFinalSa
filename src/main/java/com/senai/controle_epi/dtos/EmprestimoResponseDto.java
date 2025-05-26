package com.senai.controle_epi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoResponseDto {
    private Long id;
    private String colaboradorNome;
    private String equipamentoDescricao;
    private LocalDate data;
    private LocalDate devolucao;
    private String observacao;
}
