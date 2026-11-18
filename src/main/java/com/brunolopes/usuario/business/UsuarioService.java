package com.brunolopes.usuario.business;

import com.brunolopes.usuario.business.converter.UsuarioConverter;
import com.brunolopes.usuario.business.dto.UsuarioDTO;
import com.brunolopes.usuario.infrastructure.entity.Usuario;
import com.brunolopes.usuario.infrastructure.exceptions.ConflictException;
import com.brunolopes.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.brunolopes.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.converterParaUsuario(usuarioDTO); // Converter um UsuarioDTO para um Usuario(Entidade)
        usuario = usuarioRepository.save(usuario); // Salvar o usuario no banco de dados
        return usuarioConverter.converterParaUsuarioDTO(usuario); // Converter o usuario em UsuarioDTO
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificadorEmail(email);
            if(existe) {
                throw new ConflictException("Email já cadastrado na base de dados" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado na base de dados" + e.getCause());
        }

    }

    public boolean verificadorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException
                        ("Email não encontrado" + email));
    }

    public void deletaUsuarioPorEmail(String email){
        // Verifica se existe antes de tentar apagar
        if (!usuarioRepository.existsByEmail(email)) {
            throw new ResourceNotFoundException("Não foi possível deletar. Usuário não encontrado: " + email);
        }
        usuarioRepository.deleteByEmail(email);
    }
}
