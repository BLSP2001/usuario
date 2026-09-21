package com.brunolopes.usuario.business.converter;

import com.brunolopes.usuario.business.dto.EnderecoDTO;
import com.brunolopes.usuario.business.dto.TelefoneDTO;
import com.brunolopes.usuario.business.dto.UsuarioDTO;
import com.brunolopes.usuario.infrastructure.entity.Endereco;
import com.brunolopes.usuario.infrastructure.entity.Telefone;
import com.brunolopes.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario converterParaUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .endereco(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefone(paraListaTelefone(usuarioDTO.getTelefones()))

                .build();
    }

    //Transforma uma lista de EnderecoDTO para uma Lista de Endereco(Entidade)
    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecosDTOS) {
        return enderecosDTOS.stream().map(this::converterParaEndereco).toList();
    }

    //Transforma um atributo do EnderecoDTO na Entidade Endereco
    public Endereco converterParaEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .numero(enderecoDTO.getNumero())
                .estado(enderecoDTO.getEstado())

                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::converterParaTelefone).toList();
    }

    public Telefone converterParaTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public UsuarioDTO converterParaUsuarioDTO(Usuario usuarioDTO) {
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEndereco()))
                .telefones(paraListaTelefoneDTO(usuarioDTO.getTelefone()))

                .build();
    }

    //Transforma uma lista de EnderecoDTO para uma Lista de Endereco(Entidade)
    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecosDTOS) {
        return enderecosDTOS.stream().map(this::converterParaEnderecoDTO).toList();
    }

    //Transforma um atributo do EnderecoDTO na Entidade Endereco
    public EnderecoDTO converterParaEnderecoDTO(Endereco enderecoDTO) {
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .numero(enderecoDTO.getNumero())
                .estado(enderecoDTO.getEstado())

                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDTOS){
        return telefoneDTOS.stream().map(this::converterParaTelefoneDTO).toList();
    }

    public TelefoneDTO converterParaTelefoneDTO(Telefone telefoneDTO) {
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }
}

