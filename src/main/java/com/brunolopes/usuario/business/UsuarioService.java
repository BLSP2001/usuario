package com.brunolopes.usuario.business;

import com.brunolopes.usuario.business.converter.UsuarioConverter;
import com.brunolopes.usuario.business.dto.UsuarioDTO;
import com.brunolopes.usuario.infrastructure.entity.Usuario;
import com.brunolopes.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.converterParaUsuario(usuarioDTO); // Converter um UsuarioDTO para um Usuario(Entidade)
        usuario = usuarioRepository.save(usuario); // Salvar o usuario no banco de dados
        return usuarioConverter.converterParaUsuarioDTO(usuario); // Converter o usuario em UsuarioDTO
    }
}
