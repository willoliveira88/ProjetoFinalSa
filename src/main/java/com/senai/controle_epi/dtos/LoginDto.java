package com.senai.controle_epi.dtos;

public class LoginDto {

    private String email;

    private String senha;

    public LoginDto() {
    }

    public LoginDto(String login, String senha) {
        this.email = login;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
