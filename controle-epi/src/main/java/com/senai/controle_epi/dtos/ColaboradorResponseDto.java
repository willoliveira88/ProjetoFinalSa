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
public class ColaboradorResponseDto {
    private Long id;
    private String nome;
    private String email;
    private String funcao;
    private LocalDate nascimento;

}
