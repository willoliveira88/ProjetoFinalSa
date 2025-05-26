package com.senai.controle_epi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColaboradorRequestDto {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @NotBlank(message = "Função é obrigatória")
    private String funcao;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate nascimento;

    public @NotBlank(message = "Nome é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome é obrigatório") String nome) {
        this.nome = nome;
    }

    public @Email(message = "E-mail inválido") @NotBlank(message = "E-mail é obrigatório") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "E-mail inválido") @NotBlank(message = "E-mail é obrigatório") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Função é obrigatória") String getFuncao() {
        return funcao;
    }

    public void setFuncao(@NotBlank(message = "Função é obrigatória") String funcao) {
        this.funcao = funcao;
    }

    public @NotNull(message = "Data de nascimento é obrigatória") LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(@NotNull(message = "Data de nascimento é obrigatória") LocalDate nascimento) {
        this.nascimento = nascimento;
    }
}
