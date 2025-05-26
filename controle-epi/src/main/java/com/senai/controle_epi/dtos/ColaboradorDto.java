package com.senai.controle_epi.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColaboradorDto {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @NotBlank(message = "Função é obrigatória")
    private String funcao;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate nascimento;
}
