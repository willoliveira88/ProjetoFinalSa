package com.senai.controle_epi.service;


import com.senai.controle_epi.dtos.UsuarioRequestDto;
import com.senai.controle_epi.dtos.UsuarioResponseDto;
import com.senai.controle_epi.models.UsuarioModel;
import com.senai.controle_epi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

     @Autowired
     UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponseDto criarUsuario(UsuarioRequestDto dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        UsuarioModel usuario = UsuarioModel.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();

        usuarioRepository.save(usuario);

        return mapToResponseDTO(usuario);
    }

    public List<UsuarioResponseDto> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDto buscarPorId(Long id) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return mapToResponseDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDto atualizarUsuario(Long id, UsuarioRequestDto dto) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        usuarioRepository.save(usuario);
        return mapToResponseDTO(usuario);
    }

    @Transactional
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDto mapToResponseDTO(UsuarioModel usuario) {
        return UsuarioResponseDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}
